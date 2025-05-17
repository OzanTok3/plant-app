package com.ozantok.plantapp.domain.usecase

import com.ozantok.plantapp.domain.model.Category
import com.ozantok.plantapp.domain.repository.PlantRepository

class GetCategoriesUseCase(private val repository: PlantRepository) {
    suspend operator fun invoke(): List<Category> = repository.getCategories()
}