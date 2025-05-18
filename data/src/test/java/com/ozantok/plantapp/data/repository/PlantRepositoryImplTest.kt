package com.ozantok.plantapp.data.repository

import com.google.common.truth.Truth.assertThat
import com.ozantok.plantapp.data.remote.api.PlantApiService
import com.ozantok.plantapp.data.remote.model.CategoriesResponseDto
import com.ozantok.plantapp.data.remote.model.CategoryDto
import com.ozantok.plantapp.data.remote.model.ImageDto
import com.ozantok.plantapp.data.remote.model.QuestionDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class PlantRepositoryImplTest {

    private lateinit var apiService: PlantApiService
    private lateinit var repository: PlantRepositoryImpl

    @Before
    fun setup() {
        apiService = mock()
        repository = PlantRepositoryImpl(apiService)
    }

    @Test
    fun `getCategories returns mapped category list`() = runTest {

        val categoryDtoList = listOf(
            CategoryDto(1, "cactus", "Cactus", 0, ImageDto("image_url"))
        )
        val response = CategoriesResponseDto(data = categoryDtoList)
        whenever(apiService.getCategories()).thenReturn(response)

        val result = repository.getCategories()

        assertThat(result).hasSize(1)
        assertThat(result.first().name).isEqualTo("cactus")
        assertThat(result.first().imageUrl).isEqualTo("image_url")
    }

    @Test
    fun `getQuestions returns mapped question list`() = runTest {

        val questionDtoList = listOf(
            QuestionDto(1, "How?", "Subtitle", "img_url", "uri", 0)
        )
        whenever(apiService.getQuestions()).thenReturn(questionDtoList)

        val result = repository.getQuestions()

        assertThat(result).hasSize(1)
        assertThat(result.first().title).isEqualTo("How?")
        assertThat(result.first().imageUri).isEqualTo("img_url")
    }
}
