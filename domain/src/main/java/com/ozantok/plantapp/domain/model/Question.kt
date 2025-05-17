package com.ozantok.plantapp.domain.model

data class Question(
    val id: Int,
    val title: String,
    val subtitle: String,
    val imageUri: String,
    val uri: String,
    val order: Int
)