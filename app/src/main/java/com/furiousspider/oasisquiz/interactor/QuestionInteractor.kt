package com.furiousspider.oasisquiz.interactor

import android.content.Context
import com.furiousspider.oasisquiz.realm.RealmProvider
import com.furiousspider.oasisquiz.realm.entities.QuestionRealm
import com.furiousspider.oasisquiz.realm.entities.StringRealm
import com.furiousspider.oasisquiz.ui.base.JsonDataWrapper
import com.furiousspider.oasisquiz.ui.base.QuestionDataWrapper
import com.google.gson.Gson
import io.reactivex.Single
import io.realm.RealmList

class QuestionInteractor {
    fun update(context: Context): Single<List<QuestionRealm>> =
            Single.just(Gson().fromJson(context.assets.open("questions").bufferedReader(), JsonDataWrapper::class.java))
                    .map {
                        deleteAll()
                        val converted = convertResponseToRealm(it.questions)
                        RealmProvider.insertOrUpdate(converted)
                    }

    fun get(): Single<List<QuestionRealm>> =
            Single.fromCallable {
                RealmProvider.findAll(QuestionRealm::class.java)
            }

    private fun convertResponseToRealm(response: List<QuestionDataWrapper>): List<QuestionRealm> =
            response.map { question ->
                QuestionRealm(
                        id = question.id,
                        type = question.type,
                        difficulty = question.difficulty,
                        category = question.category,
                        question = question.question,
                        correctAnswer = question.correctAnswer,
                        incorrectAnswers = convertIncorrectAnswers(question.incorrectAnswers),
                        imageID = question.imageID
                )
            }

    private fun convertIncorrectAnswers(incorrectAnswers: List<String>): RealmList<StringRealm> {
        val list = RealmList<StringRealm>()
        list.addAll(incorrectAnswers.map {
            StringRealm(it)
        })
        return list
    }

    private fun deleteAll() {
        RealmProvider.deleteAll(QuestionRealm::class.java) {
            incorrectAnswers.deleteAllFromRealm()
        }
    }

}