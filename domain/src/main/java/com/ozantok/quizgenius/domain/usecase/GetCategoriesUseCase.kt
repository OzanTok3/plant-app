package com.ozantok.quizgenius.domain.usecase

import com.ozantok.quizgenius.domain.model.Category
import com.ozantok.quizgenius.domain.repository.PlantRepository

class GetCategoriesUseCase(private val repository: PlantRepository) {
    suspend operator fun invoke(): List<Category> = repository.getCategories()
}