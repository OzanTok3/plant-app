package com.ozantok.plantapp.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.ozantok.plantapp.domain.model.Category
import com.ozantok.plantapp.domain.repository.PlantRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GetCategoriesUseCaseTest {

    private lateinit var repository: PlantRepository
    private lateinit var useCase: GetCategoriesUseCase

    @Before
    fun setUp() {
        repository = mock()
        useCase = GetCategoriesUseCase(repository)
    }

    @Test
    fun `invoke returns category list from repository`() = runTest {
        // Given
        val categoryList = listOf(
            Category(1, "herb", "Herbs", 0, "https://example.com/img1.png"),
            Category(2, "tree", "Trees", 1, "https://example.com/img2.png")
        )
        whenever(repository.getCategories()).thenReturn(categoryList)

        // When
        val result = useCase()

        // Then
        assertThat(result).isEqualTo(categoryList)
    }
}
