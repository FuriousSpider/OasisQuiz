package com.furiousspider.oasisquiz.ui.activities.game.single.game.model

import com.furiousspider.oasisquiz.interactor.QuestionInteractor
import com.furiousspider.oasisquiz.realm.entities.StringRealm
import com.furiousspider.oasisquiz.ui.activities.game.single.game.SingleGameActivity
import com.furiousspider.oasisquiz.utils.QuestionType
import io.reactivex.Single
import io.realm.RealmList
import java.util.*
import kotlin.collections.ArrayList

class SingleGameModelCreator {

    //TODO: implement images
    fun create(): Single<List<SingleGameModel>> =
            QuestionInteractor().get()
                    .map { list ->
                        list.shuffled().take(SingleGameActivity.NUMBER_OF_QUESTIONS).map {
                            SingleGameModel(
                                    id = it.id,
                                    type = QuestionType.valueOf(it.type),
                                    question = it.question,
                                    correctAnswer = it.correctAnswer,
                                    incorrectAnswers = getIncorrectAnswers(it.incorrectAnswers),
                                    image = null
                            )
                        }
                    }

    private fun getIncorrectAnswers(incorrectAnswers: RealmList<StringRealm>): List<String> {
        if (incorrectAnswers.size < 3) {
            return emptyList()
        } else {
            val list = ArrayList<String>()
            val oldList = ArrayList<String>()
            val rand = Random()
            incorrectAnswers.forEach {
                oldList.add(it.text)
            }
            while (list.size < 3) {
                val number = rand.nextInt(oldList.size)
                list.add(oldList[number])
                oldList.removeAt(number)
            }
            return list
        }
    }
}