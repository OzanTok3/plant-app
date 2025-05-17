package com.ozantok.plantapp.data.remote.api

import com.ozantok.plantapp.data.remote.model.CategoriesResponseDto
import com.ozantok.plantapp.data.remote.model.QuestionDto
import retrofit2.http.GET

interface PlantApiService {

    @GET("getQuestions")
    suspend fun getQuestions(): List<QuestionDto>

    @GET("getCategories")
    suspend fun getCategories(): CategoriesResponseDto
}