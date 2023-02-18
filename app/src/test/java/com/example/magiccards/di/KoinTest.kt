package com.example.magiccards.di

import com.example.magiccards.data.network.CardService
import com.example.magiccards.data.repository.CardsRepository
import com.example.magiccards.ui.viewmodel.CardListViewModel
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import org.koin.test.verify.verify
import kotlin.test.assertNotNull


class KoinDependencyTest : KoinTest {

    @get:Rule
    var koinTestRule = KoinTestRule.create {
        printLogger()
        modules(appModule)
    }

    @After
    fun tearDown(){
        stopKoin()
    }

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun `check all modules`() {
        appModule.verify()
    }

    @Test
    fun `viewModel should be Injected by Koin`() {
        assertNotNull(get<CardListViewModel>())
    }

    @Test
    fun `cardService should be Injected by Koin`() {
        assertNotNull(get<CardService>())
    }

    @Test
    fun `repository should be Injected by Koin`() {
        assertNotNull(get<CardsRepository>())
    }
}