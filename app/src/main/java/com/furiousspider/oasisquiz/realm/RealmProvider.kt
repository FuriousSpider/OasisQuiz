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

    fun <T : RealmObject> insertOrUpdate(objects: List<T>): List<T> {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.insertOrUpdate(objects)
        realm.commitTransaction()
        realm.close()
        return objects
    }

    fun <T : RealmObject> findAll(clazz: Class<T>): List<T> {
        val realm = Realm.getDefaultInstance()
        val result = realm.where(clazz).findAll()
        result?.let {
            val resultObject = realm.copyFromRealm(result)
            realm.close()
            return resultObject
        } ?: run {
            realm.close()
            return ArrayList()
        }
    }

    fun <T : RealmObject> findBy(clazz: Class<T>, keyName: String, id: String): T? {
        val realm = Realm.getDefaultInstance()
        val result = realm.where(clazz).equalTo(keyName, id).findFirst()
        result?.let {
            val resultObject = realm.copyFromRealm(it)
            realm.close()
            return resultObject
        } ?: run {
            realm.close()
            return null
        }
    }

    fun <T : RealmObject> deleteAll(clazz: Class<T>, innerDeletion: (T.() -> Unit)? = null) {
        val realm = Realm.getDefaultInstance()
        val result = realm.where(clazz).findAll()
        realm.beginTransaction()
        result?.let {
            innerDeletion?.let {
                result.forEach {
                    it.innerDeletion()
                }
            }
            result.deleteAllFromRealm()
        }
        realm.commitTransaction()
        realm.close()
    }
}