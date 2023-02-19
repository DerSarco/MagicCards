package com.example.magiccards.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.magiccards.ui.theme.MagicCardsTheme

@Composable
fun MagicApp(content: @Composable () -> Unit) {
    MagicCardsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
           content()
        }
    }
}
