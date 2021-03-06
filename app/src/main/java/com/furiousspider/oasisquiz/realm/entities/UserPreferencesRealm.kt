package com.furiousspider.oasisquiz.realm.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserPreferencesRealm (
        @PrimaryKey
        var id: Long = 1L,
        var gameSingleDifficulty: String = "",
        var gameSingleCategory: String = ""
) : RealmObject()