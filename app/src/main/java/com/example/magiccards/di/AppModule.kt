package com.example.magiccards.di

import com.example.magiccards.data.network.ApiClient
import com.example.magiccards.data.network.CardService
import com.example.magiccards.data.repository.CardsRepository
import com.example.magiccards.ui.viewmodel.CardListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { ApiClient().getRetrofitInstance().create(CardService::class.java) }
    single { CardsRepository(get()) }
    viewModel { CardListViewModel(get()) }
}