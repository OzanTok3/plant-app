package com.ozantok.plantapp.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.ozantok.plantapp.data.local.dao.SearchQueryDao
import com.ozantok.plantapp.data.local.entity.SearchQueryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: SearchQueryDao
    private lateinit var viewModel: SearchViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        dao = mock(SearchQueryDao::class.java)
        viewModel = SearchViewModel(dao)
    }

    @Test
    fun `insertQuery should insert into dao`() = runTest {
        val query = "rose"
        viewModel.saveQuery(query)
        advanceUntilIdle()

        val captor = argumentCaptor<SearchQueryEntity>()
        verify(dao).insertQuery(captor.capture())

        val captured = captor.firstValue
        assertThat(captured.query).isEqualTo(query)
    }

    @Test
    fun `getAllQueries returns expected values`() = runTest {
        val queries = listOf(
            SearchQueryEntity(1, "rose", 123456L),
            SearchQueryEntity(2, "tulip", 123457L)
        )
        `when`(dao.getAllQueries()).thenReturn(flowOf(queries))

        val flow = viewModel.getAllQueries()
        flow.test {
            val result = awaitItem()
            assertThat(result).isEqualTo(queries)
            cancelAndConsumeRemainingEvents()
        }
    }
}