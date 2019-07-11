package com.furiousspider.oasisquiz.ui.base

data class JsonDataWrapper(
        val version: String,
        val questions: List<QuestionDataWrapper>
)

data class QuestionDataWrapper(
        val id: Long,
        val type: String,
        val difficulty: String,
        val category: String,
        val question: String,
        val correctAnswer: String,
        val incorrectAnswers: List<String>,
        val imageID: Long
)