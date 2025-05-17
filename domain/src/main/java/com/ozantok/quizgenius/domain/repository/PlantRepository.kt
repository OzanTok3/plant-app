package com.ozantok.quizgenius.domain.repository

import com.ozantok.quizgenius.domain.model.Category
import com.ozantok.quizgenius.domain.model.Question

interface PlantRepository {
    suspend fun getQuestions(): List<Question>
    suspend fun getCategories(): List<Category>
}