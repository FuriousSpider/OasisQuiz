package com.furiousspider.oasisquiz.ui.activities.game.single.quick.model

import com.furiousspider.oasisquiz.utils.QuestionType
import java.io.File

data class QuickSingleGameModel(
    val id: Int,
    val questionType: QuestionType,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>,
    val image: File?
)