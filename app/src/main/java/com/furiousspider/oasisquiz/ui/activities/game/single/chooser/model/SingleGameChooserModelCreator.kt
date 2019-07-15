package com.furiousspider.oasisquiz.ui.activities.game.single.chooser.model

import com.furiousspider.oasisquiz.interactor.UserPreferencesInteractor
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class SingleGameChooserModelCreator {

    fun updateDifficulty(difficulty: String): Completable =
            Completable.fromAction { UserPreferencesInteractor().updateGameSingleDifficulty(difficulty) }

    fun updateCategory(category: String): Completable =
            Completable.fromAction { UserPreferencesInteractor().updateGameSingleCategory(category) }

    fun getDifficultyAndCategory(): Single<Pair<String, String>> =
            Single.zip(
                    UserPreferencesInteractor().getGameSingleDifficulty(),
                    UserPreferencesInteractor().getGameSingleCategory(),
                    BiFunction { difficulty, category ->
                        Pair(difficulty, category)
                    }
            )
}