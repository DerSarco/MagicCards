package com.example.magiccards.data.network


import com.example.magiccards.data.network.entities.MagicCardNetwork
import com.example.magiccards.data.network.entities.MagicCardsNetwork
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CardService {
    @GET("v1/cards")
    suspend fun getCards(): Response<MagicCardsNetwork>

    @GET("v1/cards/{id}")
    suspend fun getCard(@Query("id") id: String): Response<MagicCardNetwork>
}