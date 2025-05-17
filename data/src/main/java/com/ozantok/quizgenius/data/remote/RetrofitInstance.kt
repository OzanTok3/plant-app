package com.ozantok.quizgenius.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.ozantok.quizgenius.data.remote.api.PlantApiService

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://dummy-api-jtg6bessta-ey.a.run.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: PlantApiService by lazy {
        retrofit.create(PlantApiService::class.java)
    }
}