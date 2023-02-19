package com.example.magiccards.ui.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.magiccards.R
import com.example.magiccards.ui.MagicApp
import com.example.magiccards.ui.theme.detailViewImageSize
import com.example.magiccards.ui.theme.maxPadding
import com.example.magiccards.ui.theme.mediumPadding
import com.example.magiccards.ui.theme.welcomePadding

@Composable
fun Welcome(onClick: () -> Unit) {
    val modifier = Modifier.padding(welcomePadding)
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier
                .size(detailViewImageSize)
                .clip(RoundedCornerShape(12.dp)),
            painter = painterResource(id = R.drawable.dev),
            contentDescription = stringResource(R.string.developer_text)
        )

        Text(
            text = stringResource(id = R.string.name),
            fontSize = 24.sp,
            modifier = modifier
        )
        Text(
            text = stringResource(id = R.string.mail),
            fontSize = 24.sp,
            modifier = modifier
        )

        Text(
            text = stringResource(id = R.string.date),
            fontSize = 24.sp,
            modifier = modifier
        )

        Text(
            text = stringResource(id = R.string.hours),
            fontSize = 24.sp,
            modifier = modifier
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = maxPadding, vertical = mediumPadding),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .clickable {
                        goToWeb(context.getString(R.string.github_uri), uriHandler)
                    }
                    .size(maxPadding),
                painter = painterResource(id = R.drawable.github),
                contentDescription = stringResource(R.string.github_icon),
            )
            Icon(
                modifier = Modifier
                    .clickable {
                        goToWeb(context.getString(R.string.discord_uri), uriHandler)
                    }
                    .size(maxPadding),
                painter = painterResource(id = R.drawable.discord),
                contentDescription = stringResource(R.string.discord_icon)
            )
            Icon(
                modifier = Modifier
                    .clickable {
                        goToWeb(context.getString(R.string.linkedin_uri), uriHandler)
                    }
                    .size(maxPadding),
                painter = painterResource(id = R.drawable.linkedin),
                contentDescription = stringResource(R.string.linkedin_icon)
            )
        }

        Button(modifier = Modifier.padding(top = maxPadding), onClick = { onClick() }) {
            Text(text = stringResource(id = R.string.welcomeButtonText))
        }
    }
}

fun goToWeb(uri: String, uriHandler: UriHandler) {
    uriHandler.openUri(uri)
}


@Preview(showSystemUi = true)
@Composable
fun WelcomePrev() {
    MagicApp {
        Welcome {
        }
    }
}
