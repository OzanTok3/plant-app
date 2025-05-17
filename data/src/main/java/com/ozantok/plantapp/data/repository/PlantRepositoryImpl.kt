package com.ozantok.plantapp.data.repository

import com.ozantok.plantapp.data.mapper.toDomain
import com.ozantok.plantapp.data.remote.api.PlantApiService

import com.ozantok.plantapp.domain.model.Category
import com.ozantok.plantapp.domain.model.Question
import com.ozantok.plantapp.domain.repository.PlantRepository

class PlantRepositoryImpl(
    private val apiService: PlantApiService
) : PlantRepository {

    override suspend fun getQuestions(): List<Question> =
        apiService.getQuestions().map { it.toDomain() }

    override suspend fun getCategories(): List<Category> =
        apiService.getCategories().data.map { it.toDomain() }
}