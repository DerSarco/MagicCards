package com.example.magiccards.data.repository

import com.example.magiccards.data.entities.Card
import com.example.magiccards.data.entities.Cards
import com.example.magiccards.data.network.CardService
import com.example.magiccards.data.network.entities.CardsNetwork

class CardsRepository(private val cardService: CardService) {

    private val cache = arrayListOf<Cards>()

    suspend fun getCards(page: Int): List<Card> {
        val find = cache.find {
            page == it.page
        }
        return if (find == null) {
            val cardNetworkResponse = cardService.getCards()
            cache.add(Cards((cache.size + 1), cardNetworkResponse.toCard()))
            cache[page - 1].cards
        } else find.cards
    }
}

fun CardsNetwork.toCard() =
    this.cards.map { networkCard ->
        Card(
            id = networkCard.id,
            artist = networkCard.artist,
            name = networkCard.name,
            imageUrl = networkCard.imageUrl?.toHttps(),
            manaCost = networkCard.manaCost,
            rarity = networkCard.rarity,
            text = networkCard.text
        )
    }

@SuppressWarnings()
fun String.toHttps(): String =
    this.replace("http", "https")

