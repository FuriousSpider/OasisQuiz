package com.oasis.oasisquiz.model

data class Question(
    val question: String = "",
    val correctAnswer: String = "",
    val incorrectAnswers: List<String> = emptyList()
)