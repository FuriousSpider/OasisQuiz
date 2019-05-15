package com.furiousspider.oasisquiz.realm

import android.content.Context
import io.realm.Realm
import io.realm.RealmObject

object RealmProvider {

    fun init(context: Context) {
        Realm.init(context)
    }

    fun <T : RealmObject> insertOrUpdate(`object`: T): T? {
        val realm = Realm.getDefaultInstance()
        realm.use { r ->
            r.beginTransaction()
            r.insertOrUpdate(`object`)
            r.commitTransaction()
            r.close()
            return `object`
        }
    }

    fun <T : RealmObject> getBy(clazz: Class<T>, keyName: String, id: String): T? {
        val realm = Realm.getDefaultInstance()
        val result = realm.where(clazz).equalTo(keyName, id).findFirst()
        result?.let {
            val resultObject = realm.copyFromRealm(it)
            realm.close()
            return resultObject
        } ?:kotlin.run {
            realm.close()
            return null
        }
    }
}