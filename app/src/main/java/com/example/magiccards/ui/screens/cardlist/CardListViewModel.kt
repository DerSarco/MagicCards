package com.example.magiccards.ui.screens.cardlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.magiccards.data.entities.Card
import com.example.magiccards.data.repository.CardsRepository

class CardListViewModel(
    private val repository: CardsRepository
) : ViewModel() {

    private val page = MutableLiveData(1)

    suspend fun getList(): List<Card> {
       return repository.getCards(page.value!!)
    }

    fun nextPage() {
        page.postValue(page.value?.plus(1))
    }

}