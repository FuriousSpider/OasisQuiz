package com.furiousspider.oasisquiz.ui.activities.game.single.quick.model

import com.furiousspider.oasisquiz.utils.QuestionType
import io.reactivex.Single

class QuickSingleGameModelCreator {

    //TODO: temporary solution, implement when data generated
    fun create(): Single<List<QuickSingleGameModel>> = Single.just(listOf(
            QuickSingleGameModel(
                    id = 1,
                    questionType = QuestionType.SIMPLE_QUESTION,
                    question = "What's up?",
                    correctAnswer = "OK",
                    incorrectAnswers = listOf("I'm not feeling so good", "Bad", "It used to be better"),
                    image = null),
            QuickSingleGameModel(
                    id = 1,
                    questionType = QuestionType.SIMPLE_QUESTION,
                    question = "What is going on?",
                    correctAnswer = "OK",
                    incorrectAnswers = listOf("I'm not feeling so good", "Bad", "It used to be better"),
                    image = null),
            QuickSingleGameModel(
                    id = 1,
                    questionType = QuestionType.SIMPLE_QUESTION,
                    question = "Are you ok?",
                    correctAnswer = "OK",
                    incorrectAnswers = listOf("I'm not feeling so good", "Bad", "It used to be better"),
                    image = null)

    ))

}