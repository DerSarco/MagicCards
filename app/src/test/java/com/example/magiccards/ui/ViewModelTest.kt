package com.example.magiccards.ui

import com.example.magiccards.data.repository.CardRepository
import com.example.magiccards.ui.viewmodel.CardListViewModel
import com.example.magiccards.util.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ViewModelTest {
    @get:Rule
    val mockKRule = MockKRule(this)

    @get:Rule
    val dispatcher = MainCoroutineRule()

    private val cardRepository = mockk<CardRepository>()
    private val mViewModel = CardListViewModel(cardRepository)
    private val fakeCardList = getFakeCardList()
    private val fakeCard = getFakeCard()
    private val fakeErrorCardList = getFakeErrorCardList()
    private val fakeErrorCard = getFakeErrorCard()

    @Test
    fun `viewModel SHOULD fetch a cards list from service WHEN function is called`() = runTest {
        coEvery{ cardRepository.getCards()} returns fakeCardList

        val response = mViewModel.getList()

        coVerify {
            cardRepository.getCards()
        }

        response shouldBeEqualTo fakeCardList
    }

    @Test
    fun `viewModel SHOULD throw error WHEN error is triggered`() = runTest {
        coEvery{ cardRepository.getCards()} returns fakeErrorCardList

        val response = mViewModel.getList()

        coVerify {
            cardRepository.getCards()
        }
        response shouldBeEqualTo fakeErrorCardList
    }

    @Test
    fun `viewModel SHOULD fetch a card from service WHEN function is called`() = runTest {
        coEvery{ cardRepository.getCard("id")} returns fakeCard

        val response = mViewModel.getCard("id")

        coVerify {
            cardRepository.getCard("id")
        }

        response shouldBeEqualTo fakeCard
    }

    @Test
    fun `viewModel SHOULD throw error WHEN card is not fetched`() = runTest {
        coEvery{ cardRepository.getCard("id")} returns fakeErrorCard

        val response = mViewModel.getCard("id")

        coVerify {
            cardRepository.getCard("id")
        }
        response shouldBeEqualTo fakeErrorCard
    }
}
