package com.ngr.appnews

import com.ngr.appnews.viewmodel.ListArticleEvent
import com.ngr.appnews.viewmodel.ListArticleState
import com.ngr.appnews.viewmodel.ListArticlesViewModel
import com.ngr.domain.usecase.ArticlesListUseCase
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ListArticlesViewModelTest {
    private lateinit var viewModel: ListArticlesViewModel
    private lateinit var articlesListUseCase: ArticlesListUseCase

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        articlesListUseCase = mockk<ArticlesListUseCase>(relaxed = true)
        viewModel = ListArticlesViewModel(articlesListUseCase)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `get a articles list after call Refresh`() = runTest {

        coEvery { articlesListUseCase.invoke() } returns flowOf(emptyList())

        viewModel.dispatch(ListArticleEvent.Refresh)

        coVerify { articlesListUseCase.invoke() }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when articlesListUseCase fails, viewState should be with error`() = runTest {
        val mockError = Throwable("Mock error")
        coEvery { articlesListUseCase.invoke() } returns flow { throw mockError }

        val expectedState = ListArticleState(
            articles = emptyList(),
            isLoading = false,
            isError = mockError
        )

        viewModel.dispatch(ListArticleEvent.Refresh)

        advanceUntilIdle()

        assertEquals(expectedState,viewModel.viewState.value)
    }
}