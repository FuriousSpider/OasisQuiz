package com.furiousspider.oasisquiz.interactor

import android.content.Context
import com.furiousspider.oasisquiz.realm.RealmProvider
import com.furiousspider.oasisquiz.realm.entities.DataBaseVersionRealm
import com.furiousspider.oasisquiz.ui.base.VersionDataWrapper
import com.google.gson.Gson
import io.reactivex.Single

class SettingsInteractor {

    fun checkIfVersionIsUpToDate(context: Context): Single<Boolean> =
        Single.just(Gson().fromJson(context.assets.open("version").bufferedReader(), VersionDataWrapper::class.java))
            .map {version ->
                val currentVersion = RealmProvider.findBy(DataBaseVersionRealm::class.java, "id", 1L)
                currentVersion?.let {
                    it.version == version.version
                } ?: false
            }
}