package com.example.magiccards.data.repository

import com.example.magiccards.data.entities.MagicCardEntity
import com.example.magiccards.data.network.ApiResponse

interface CardRepository {
    suspend fun getCards(): ApiResponse<List<MagicCardEntity>>
    suspend fun getCard(id: String): ApiResponse<MagicCardEntity>?
}
