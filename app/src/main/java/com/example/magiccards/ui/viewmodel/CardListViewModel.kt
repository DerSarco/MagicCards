package com.example.magiccards.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.magiccards.data.entities.LocalMagicCard
import com.example.magiccards.data.network.ApiResponse
import com.example.magiccards.data.repository.CardInterface

class CardListViewModel(
    private val repository: CardInterface
) : ViewModel() {

    private val page = MutableLiveData(1)

    suspend fun getList(): ApiResponse<List<LocalMagicCard>> {
        return repository.getCards(page.value!!)
    }

    fun nextPage() {
        page.postValue(page.value?.plus(1))
    }

    suspend fun getCard(id: String): ApiResponse<LocalMagicCard>? {
        return repository.getCard(id)
    }
}