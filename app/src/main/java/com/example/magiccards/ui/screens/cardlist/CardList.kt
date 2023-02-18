package com.example.magiccards.ui.screens.cardlist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.magiccards.data.entities.LocalMagicCard

@Composable
fun CardsList(localMagicCards: List<LocalMagicCard>, onItemClick: (String) -> Unit) {
    LazyColumn {
        items(items = localMagicCards) { cardData ->
            CardItem(cardData, onItemClick)
        }
    }
}