package com.example.magiccards.data.repository

import com.example.magiccards.ui.viewmodel.CardListViewModel
import com.example.magiccards.util.MainCoroutineRule
import com.example.magiccards.util.getFakeCardList
import com.example.magiccards.util.getFakeErrorCardList
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CardRepositoryTest{

    @get:Rule
    val mockKRule = MockKRule(this)

    @get:Rule
    val dispatcher = MainCoroutineRule()

    private val fakeRepository = mockk<CardInterface>()
    private val mViewModel = CardListViewModel(fakeRepository)
    private val fakeCardList = getFakeCardList()
    private val fakeErrorCardList = getFakeErrorCardList()
    private val page = 1


    @Test
    fun `viewModel SHOULD fetch cards from service WHEN function is called`() = runTest{
         coEvery{ fakeRepository.getCards(page)} returns fakeCardList

        val response = mViewModel.getList()

        coVerify {
            fakeRepository.getCards(page)
        }

        response shouldBeEqualTo fakeCardList
    }

    @Test
    fun `viewModel SHOULD throw error WHEN data is not fetched`() = runTest{
        coEvery{ fakeRepository.getCards(page)} returns fakeErrorCardList

        val response = mViewModel.getList()

        coVerify {
            fakeRepository.getCards(page)
        }

        response shouldBeEqualTo fakeErrorCardList
    }

}