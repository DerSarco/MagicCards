package com.example.magiccards.data.repository

import com.example.magiccards.data.entities.LocalMagicCard
import com.example.magiccards.data.network.ApiResponse

interface CardInterface {
    suspend fun getCards(page: Int): ApiResponse<List<LocalMagicCard>>
    suspend fun getCard(id: String): ApiResponse<LocalMagicCard>?
}