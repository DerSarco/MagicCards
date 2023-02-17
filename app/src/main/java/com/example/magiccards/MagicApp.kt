package com.example.magiccards

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MovableContent
import androidx.compose.ui.Modifier
import com.example.magiccards.ui.theme.MagicCardsTheme

@Composable
fun MagicApp(content: @Composable () -> Unit) {
    MagicCardsTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
           content()
        }
    }
}