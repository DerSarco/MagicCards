package com.example.magiccards.data.repository

import com.example.magiccards.data.entities.MagicCardEntity
import com.example.magiccards.data.network.ApiResponse
import com.example.magiccards.data.network.CardService
import com.example.magiccards.util.*
import com.google.gson.Gson
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.amshove.kluent.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

private const val CODE_OK = HttpURLConnection.HTTP_OK
private const val CODE_NOK = HttpURLConnection.HTTP_NOT_FOUND

@OptIn(ExperimentalCoroutinesApi::class)
class CardRepositoryTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    private lateinit var network: CardService
    private lateinit var repository: DefaultCardsRepository
    private lateinit var mockWebServer: MockWebServer

    object RetrofitHelper {
        fun getInstances(url: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        network = RetrofitHelper.getInstances(mockWebServer.url("/").toString())
            .create(CardService::class.java)
        repository = DefaultCardsRepository(network)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun `repository SHOULD fetch data WHEN service is called`() = runTest {
        mockWebResponse(getFakeCardListService(), CODE_OK)
        val response = repository.getCards() as ApiResponse.Success<*>
        val data = response.data as List<MagicCardEntity>

        response shouldHaveTheSameClassAs getFakeCardList()
        data.first().id shouldBeEqualTo getFakeCardListService().cards.first().id
    }

    @Test
    fun `repository SHOULD return ApiResponse Error WHEN response is unsuccessful`() = runTest {
        mockWebResponse(getFakeCardListService(), HttpURLConnection.HTTP_NOT_FOUND)

        val response = repository.getCards() as ApiResponse.Error<*>

        response::class.java shouldHaveTheSameClassAs ApiResponse.Error::class.java
        response.code shouldBeEqualTo HttpURLConnection.HTTP_NOT_FOUND
    }

    @Test
    fun `repository SHOULD return a card WHEN response is successful`() = runTest {
        mockWebResponse(getFakeCardService(), CODE_OK)

        val response = repository.getCard("1") as ApiResponse.Success<MagicCardEntity>

        response::class.java shouldHaveTheSameClassAs ApiResponse.Success::class.java
        response.data.id shouldBeEqualTo getFakeCardService().id

    }

    @Test
    fun `repository SHOULD return an API Error Response WHEN response is unsuccessful`() = runTest {
        mockWebResponse(getFakeCardService(), CODE_NOK)

        val response = repository.getCard("1") as ApiResponse.Error<MagicCardEntity>

        response::class.java shouldHaveTheSameClassAs ApiResponse.Success::class.java
        response.code shouldBeEqualTo CODE_NOK

    }

    private fun <T : Any> mockWebResponse(response: T, responseCode: Int) {
        val expectedResponse = MockResponse().setResponseCode(responseCode)
            .setBody(Gson().toJson(response))

        mockWebServer.enqueue(expectedResponse)
    }

}