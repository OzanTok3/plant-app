package com.ozantok.quizgenius.data.repository

import com.ozantok.quizgenius.data.mapper.toDomain
import com.ozantok.quizgenius.data.remote.api.PlantApiService

import com.ozantok.quizgenius.domain.model.Category
import com.ozantok.quizgenius.domain.model.Question
import com.ozantok.quizgenius.domain.repository.PlantRepository

class PlantRepositoryImpl(
    private val apiService: PlantApiService
) : PlantRepository {

    override suspend fun getQuestions(): List<Question> =
        apiService.getQuestions().map { it.toDomain() }

    override suspend fun getCategories(): List<Category> =
        apiService.getCategories().data.map { it.toDomain() }
}