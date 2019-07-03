package com.furiousspider.oasisquiz.realm.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class MetaDataRealm(
        @PrimaryKey
        var id: Long = 1L,
        var questionsVersion: String = ""
) : RealmObject()