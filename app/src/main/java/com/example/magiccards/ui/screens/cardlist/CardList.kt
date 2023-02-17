package com.example.magiccards.ui.screens.cardlist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.magiccards.data.entities.Card
import com.example.magiccards.data.entities.Cards
import com.example.magiccards.data.entities.getColumnTestData

@Composable
fun CardsList(cards: List<Card>, onItemClick: (String) -> Unit) {
    LazyColumn {
        items(items = cards) { cardData ->
            CardItem(cardData, onItemClick)
        }
    }
}