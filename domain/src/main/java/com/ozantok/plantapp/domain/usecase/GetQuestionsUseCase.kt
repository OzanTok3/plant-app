package com.ozantok.plantapp.domain.usecase

import com.ozantok.plantapp.domain.model.Question
import com.ozantok.plantapp.domain.repository.PlantRepository

class GetQuestionsUseCase(private val repository: PlantRepository) {
    suspend operator fun invoke(): List<Question> = repository.getQuestions()
}