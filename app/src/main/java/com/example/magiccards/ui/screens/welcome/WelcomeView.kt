package com.example.magiccards.ui.screens.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.magiccards.MagicApp
import com.example.magiccards.R

@Composable
fun Welcome(onClick: () -> Unit) {
    val modifier = Modifier.padding(14.dp)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
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

        Button(onClick = { onClick() }) {
            Text(text = stringResource(id = R.string.welcomeButtonText))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun WelcomePrev() {
    MagicApp {
        Welcome {

        }
    }
}