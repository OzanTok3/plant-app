package com.ozantok.plantapp.di

import com.ozantok.plantapp.data.remote.api.PlantApiService
import com.ozantok.plantapp.data.repository.PlantRepositoryImpl
import com.ozantok.plantapp.domain.repository.PlantRepository
import com.ozantok.plantapp.domain.usecase.GetCategoriesUseCase
import com.ozantok.plantapp.domain.usecase.GetQuestionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://dummy-api-jtg6bessta-ey.a.run.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providePlantApiService(retrofit: Retrofit): PlantApiService =
        retrofit.create(PlantApiService::class.java)

    @Provides
    @Singleton
    fun providePlantRepository(apiService: PlantApiService): PlantRepository =
        PlantRepositoryImpl(apiService)

    @Provides
    fun provideGetQuestionsUseCase(repository: PlantRepository): GetQuestionsUseCase =
        GetQuestionsUseCase(repository)

    @Provides
    fun provideGetCategoriesUseCase(repository: PlantRepository): GetCategoriesUseCase =
        GetCategoriesUseCase(repository)
}
