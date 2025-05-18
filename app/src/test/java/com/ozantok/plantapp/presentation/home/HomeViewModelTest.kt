package com.ozantok.plantapp.presentation.home

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.ozantok.core.util.UIState
import com.ozantok.plantapp.domain.model.Category
import com.ozantok.plantapp.domain.model.Question
import com.ozantok.plantapp.domain.usecase.GetCategoriesUseCase
import com.ozantok.plantapp.domain.usecase.GetQuestionsUseCase
import com.ozantok.plantapp.util.TestDispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var getQuestionsUseCase: GetQuestionsUseCase
    private lateinit var getCategoriesUseCase: GetCategoriesUseCase
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        getQuestionsUseCase = mock()
        getCategoriesUseCase = mock()
        val testDispatcherProvider = TestDispatcherProvider()
        viewModel = HomeViewModel(getQuestionsUseCase, getCategoriesUseCase, testDispatcherProvider)
    }

    @Test
    fun `fetchHomeData emits success state with data`() = runTest {

        val questions = listOf(Question(1, "Title", "Sub", "img", "uri", 0))
        val categories = listOf(Category(1, "Name", "Title", 0, "img"))

        whenever(getQuestionsUseCase()).thenReturn(questions)
        whenever(getCategoriesUseCase()).thenReturn(categories)


        viewModel.fetchHomeData()
        advanceUntilIdle()


        assertThat(viewModel.uiState.value).isEqualTo(UIState.SUCCESS)
        assertThat(viewModel.questions.value).isEqualTo(questions)
        assertThat(viewModel.categories.value).isEqualTo(categories)
    }

    @Test
    fun `fetchHomeData emits error state when exception thrown`() = runTest {
        whenever(getQuestionsUseCase()).thenThrow(RuntimeException("Test error"))

        viewModel.fetchHomeData()
        advanceUntilIdle()

        assertThat(viewModel.uiState.value).isEqualTo(UIState.ERROR)
    }
}
