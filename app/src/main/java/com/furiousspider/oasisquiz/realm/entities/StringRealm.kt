package com.furiousspider.oasisquiz.realm.entities

import io.realm.RealmObject

open class StringRealm(
        var text: String = ""
) : RealmObject()