package com.ozantok.quizgenius.domain.usecase

import com.ozantok.quizgenius.domain.model.Question
import com.ozantok.quizgenius.domain.repository.PlantRepository

class GetQuestionsUseCase(private val repository: PlantRepository) {
    suspend operator fun invoke(): List<Question> = repository.getQuestions()
}