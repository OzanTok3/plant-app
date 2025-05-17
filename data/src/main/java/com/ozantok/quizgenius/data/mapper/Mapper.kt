package com.ozantok.quizgenius.data.mapper

import com.ozantok.quizgenius.data.remote.model.CategoryDto
import com.ozantok.quizgenius.data.remote.model.QuestionDto
import com.ozantok.quizgenius.domain.model.Category
import com.ozantok.quizgenius.domain.model.Question

fun CategoryDto.toDomain(): Category = Category(
    id = id,
    name = name,
    title = title,
    rank = rank,
    imageUrl = image.url
)

fun QuestionDto.toDomain(): Question = Question(
    id = id,
    title = title,
    subtitle = subtitle,
    imageUri = imageUri,
    uri = uri,
    order = order
)