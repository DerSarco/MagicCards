package com.example.magiccards.data.repository

import com.example.magiccards.data.entities.MagicCardEntity
import com.example.magiccards.data.entities.MagicCardsEntity
import com.example.magiccards.data.network.ApiResponse
import com.example.magiccards.data.network.CardService

class DefaultCardsRepository(private val cardService: CardService): CardRepository {

    private val cache = arrayListOf<MagicCardsEntity>()

    override suspend fun getCards(): ApiResponse<List<MagicCardEntity>> {
        val find = cache.firstOrNull()
        if (find != null) {
            return ApiResponse.Success(find.localMagicCards)
        } else {
            try {
                val cardNetworkResponse = cardService.getCards()
                return when (cardNetworkResponse.isSuccessful) {
                    true -> {
                        cardNetworkResponse.body()?.let { MagicCardsEntity(it.toCard()) }
                            ?.let { cache.add(it) }
                        ApiResponse.Success(cache.first().localMagicCards)
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

    override suspend fun getCard(id: String): ApiResponse<MagicCardEntity>? {
        var found: MagicCardEntity? = null
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
            } catch (e: Exception) {
                return ApiResponse.Error(0, "Network Error")
            }
        }
    }
}
