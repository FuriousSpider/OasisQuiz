package com.furiousspider.oasisquiz.interactor

import com.furiousspider.oasisquiz.realm.RealmProvider
import com.furiousspider.oasisquiz.realm.entities.UserPreferencesRealm
import com.furiousspider.oasisquiz.utils.QuestionCategory
import com.furiousspider.oasisquiz.utils.QuestionDifficulty
import io.reactivex.Single

class UserPreferencesInteractor {

    fun updateGameSingleDifficulty(difficulty: String): Single<UserPreferencesRealm> {
        var realmObject = RealmProvider.findBy(UserPreferencesRealm::class.java, "id", 1L)
        realmObject?.let {
            it.gameSingleDifficulty = difficulty
            RealmProvider.insertOrUpdate(it)
            return Single.just(it)
        }

        realmObject = UserPreferencesRealm(gameSingleDifficulty = difficulty)
        RealmProvider.insertOrUpdate(realmObject)
        return Single.just(realmObject)
    }

    fun updateGameSingleCategory(category: String): Single<UserPreferencesRealm> {
        var realmObject = RealmProvider.findBy(UserPreferencesRealm::class.java, "id", 1L)
        realmObject?.let {
            it.gameSingleCategory = category
            RealmProvider.insertOrUpdate(it)
            return Single.just(it)
        }

        realmObject = UserPreferencesRealm(gameSingleCategory = category)
        RealmProvider.insertOrUpdate(realmObject)
        return Single.just(realmObject)
    }

    fun getGameSingleDifficulty(): Single<String> =
            Single.just(RealmProvider.findBy(UserPreferencesRealm::class.java, "id", 1L)?.gameSingleDifficulty
                    ?: QuestionDifficulty.values().first().toString())


    fun getGameSingleCategory(): Single<String> =
            Single.just(RealmProvider.findBy(UserPreferencesRealm::class.java, "id", 1L)?.gameSingleCategory
                    ?: QuestionCategory.values().first().toString())
}