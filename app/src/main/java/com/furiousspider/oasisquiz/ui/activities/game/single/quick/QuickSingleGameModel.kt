package com.furiousspider.oasisquiz.ui.activities.game.single.quick

import java.io.File

data class QuickSingleGameModel(
    val id: Int,
    val questionType: Int,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>?,
    val image: File?
)