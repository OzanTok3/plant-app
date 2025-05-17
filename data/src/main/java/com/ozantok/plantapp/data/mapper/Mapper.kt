package com.ozantok.plantapp.data.mapper

import com.ozantok.plantapp.data.remote.model.CategoryDto
import com.ozantok.plantapp.data.remote.model.QuestionDto
import com.ozantok.plantapp.domain.model.Category
import com.ozantok.plantapp.domain.model.Question

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