package com.example.magiccards.data.network

import com.example.magiccards.data.network.entities.CardsNetwork
import retrofit2.http.GET

interface CardService {
    @GET("v1/cards")
    suspend fun getCards(): CardsNetwork
}