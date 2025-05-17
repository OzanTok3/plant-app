package com.ozantok.plantapp.domain.repository

import com.ozantok.plantapp.domain.model.Category
import com.ozantok.plantapp.domain.model.Question

interface PlantRepository {
    suspend fun getQuestions(): List<Question>
    suspend fun getCategories(): List<Category>
}