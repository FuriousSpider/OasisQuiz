package com.furiousspider.oasisquiz.realm.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class DataBaseVersionRealm(
        @PrimaryKey
        var id: Long = 1L,
        var version: String = ""
) : RealmObject()