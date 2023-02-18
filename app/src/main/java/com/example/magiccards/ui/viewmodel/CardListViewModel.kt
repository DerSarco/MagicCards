package com.example.magiccards.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.magiccards.data.network.ApiResponse
import com.example.magiccards.data.repository.CardsRepository

class CardListViewModel(
    private val repository: CardsRepository
) : ViewModel() {

    private val page = MutableLiveData(1)

    suspend fun getList(): ApiResponse {
        return repository.getCards(page.value!!)
    }

    fun nextPage() {
        page.postValue(page.value?.plus(1))
    }

    suspend fun getCard(id: String): ApiResponse? {
        return repository.getCard(id)
    }
}