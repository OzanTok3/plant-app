package com.ozantok.plantapp.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.ozantok.plantapp.domain.model.Question
import com.ozantok.plantapp.domain.repository.PlantRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GetQuestionsUseCaseTest {

    private lateinit var repository: PlantRepository
    private lateinit var useCase: GetQuestionsUseCase

    @Before
    fun setUp() {
        repository = mock()
        useCase = GetQuestionsUseCase(repository)
    }

    @Test
    fun `invoke returns question list from repository`() = runTest {
        // Given
        val questionList = listOf(
            Question(1, "Title1", "Subtitle1", "image1", "uri1", 0),
            Question(2, "Title2", "Subtitle2", "image2", "uri2", 1)
        )
        whenever(repository.getQuestions()).thenReturn(questionList)

        // When
        val result = useCase()

        // Then
        assertThat(result).isEqualTo(questionList)
    }
}