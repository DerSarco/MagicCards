package com.example.magiccards.ui.common

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.magiccards.ui.theme.listItemImageSize
import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


@Composable
fun MyAsyncImage(url: String, imageSize: Dp, context: Context) {
    val untrustedImageLoader: ImageLoader = initUntrustImageLoader(context)

    val request = ImageRequest.Builder(context)
        .data(url)
        .crossfade(true)
        .diskCachePolicy(CachePolicy.ENABLED)
        .build()

    AsyncImage(
        modifier = Modifier.size(imageSize),
        model = request,
        imageLoader = untrustedImageLoader,
        contentDescription = "generic image"
    )
}

/**
 * Issues with the ssl certificates of magic the gathering page, i found this resource on
 * stackoverflow to help me out with this issue
 * https://stackoverflow.com/questions/70506905/how-to-load-image-in-coil-with-https-not-secure-certificate
 */

@SuppressLint("CustomX509TrustManager")
private fun initUntrustImageLoader(context: Context): ImageLoader {
    // Create a trust manager that does not validate certificate chains
    val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
        @SuppressLint("TrustAllX509TrustManager")
        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        @SuppressLint("TrustAllX509TrustManager")
        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return arrayOf()
        }
    })

    // Install the all-trusting trust manager
    val sslContext = SSLContext.getInstance("SSL")
    sslContext.init(null, trustAllCerts, SecureRandom())

    // Create an ssl socket factory with our all-trusting manager
    val sslSocketFactory = sslContext.socketFactory

    val client = OkHttpClient.Builder()
        .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        .hostnameVerifier { _, _ -> true }.build()


    return ImageLoader.Builder(context)
        .okHttpClient(client)
        .build()
}