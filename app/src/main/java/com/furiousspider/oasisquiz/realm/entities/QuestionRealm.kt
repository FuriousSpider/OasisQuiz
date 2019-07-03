package com.furiousspider.oasisquiz.realm.entities

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class QuestionRealm(
        @PrimaryKey
        var id: Long = 0L,
        var type: String = "",
        var difficulty: String = "",
        var question: String = "",
        var correctAnswer: String = "",
        var incorrectAnswers: RealmList<StringRealm> = RealmList(),
        var imageID: Long = 0L
) : RealmObject()