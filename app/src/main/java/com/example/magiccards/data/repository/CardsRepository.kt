package com.example.magiccards.data.repository

import android.util.AndroidRuntimeException
import com.example.magiccards.data.entities.LocalMagicCard
import com.example.magiccards.data.entities.LocalMagicCards
import com.example.magiccards.data.network.ApiResponse
import com.example.magiccards.data.network.CardService
import com.example.magiccards.data.network.entities.MagicCardNetwork
import com.example.magiccards.data.network.entities.MagicCardsNetwork

class CardsRepository(private val cardService: CardService): CardInterface {

    private val cache = arrayListOf<LocalMagicCards>()

    override suspend fun getCards(
        page: Int,
    ): ApiResponse<List<LocalMagicCard>> {
        val find = cache.find {
            page == it.page
        }
        if (find != null) {
            return ApiResponse.Success(find.localMagicCards)
        } else {
            try {
                val cardNetworkResponse = cardService.getCards()
                return when (cardNetworkResponse.isSuccessful) {
                    true -> {
                        cardNetworkResponse.body()?.let { LocalMagicCards((page), it.toCard()) }
                            ?.let { cache.add(it) }
                        ApiResponse.Success(cache[page - 1].localMagicCards)
                    }
                    else -> {
                        ApiResponse.Error(
                            cardNetworkResponse.code(),
                            cardNetworkResponse.message()
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return ApiResponse.Error(0, "Network Exception")
            }
        }
    }

    override suspend fun getCard(id: String): ApiResponse<LocalMagicCard>? {
        var found: LocalMagicCard? = null
        for (cardList in cache) {
            found = cardList.localMagicCards.find { it.id == id }
        }
        return if (found != null) {
            ApiResponse.Success(found)
        } else {
            try {
                val cardResponse = cardService.getCard(id)
                when (cardResponse.isSuccessful) {
                    true -> {
                        cardResponse.body()
                            ?.let { ApiResponse.Success(it.toSingleCardEntity()) }
                    }
                    else -> {
                        ApiResponse.Error(cardResponse.code(), cardResponse.message())
                    }
                }
            } catch (e: AndroidRuntimeException) {
                return ApiResponse.Error(0, "Network Error")
            }
        }
    }

    private fun MagicCardNetwork.toSingleCardEntity() = LocalMagicCard(
        id = this.id,
        artist = this.artist,
        name = this.name,
        imageUrl = this.imageUrl?.toHttps(),
        manaCost = this.manaCost,
        rarity = this.rarity,
        text = this.text
    )

    private fun MagicCardsNetwork.toCard() =
        this.cards.map { cardNetwork ->
            LocalMagicCard(
                id = cardNetwork.id,
                artist = cardNetwork.artist,
                name = cardNetwork.name,
                imageUrl = cardNetwork.imageUrl?.toHttps(),
                manaCost = cardNetwork.manaCost,
                rarity = cardNetwork.rarity,
                text = cardNetwork.text
            )
        }

    private fun String.toHttps(): String =
        this.replace("http", "https")
}