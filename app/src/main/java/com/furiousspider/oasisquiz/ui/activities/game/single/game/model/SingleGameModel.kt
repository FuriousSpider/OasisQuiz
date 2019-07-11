package com.furiousspider.oasisquiz.ui.activities.game.single.game.model

import com.furiousspider.oasisquiz.utils.QuestionType
import java.io.File

data class SingleGameModel(
        val id: Long,
        val type: QuestionType,
        val question: String,
        val correctAnswer: String,
        val incorrectAnswers: List<String>,
        val image: File?
)