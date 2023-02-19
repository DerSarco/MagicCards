package com.example.magiccards.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.magiccards.data.entities.MagicCardEntity
import com.example.magiccards.data.network.ApiResponse
import com.example.magiccards.data.repository.CardRepository

class CardListViewModel(
    private val repository: CardRepository
) : ViewModel() {

    suspend fun getList(): ApiResponse<List<MagicCardEntity>> {
        return repository.getCards()
    }

    suspend fun getCard(id: String): ApiResponse<MagicCardEntity>? {
        return repository.getCard(id)
    }
}