package com.furiousspider.oasisquiz.ui.activities.game.single.chooser

import com.furiousspider.oasisquiz.ui.activities.game.single.chooser.model.SingleGameChooserModelCreator
import com.furiousspider.oasisquiz.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SingleGameChooserPresenter(view: SingleGameChooserActivity) : BasePresenter<SingleGameChooserActivity>(view) {

    override fun onCreate() {
        view?.initListeners()
        view?.initSpinners()
        getDifficultyAndCategory()
    }

    private fun getDifficultyAndCategory() {
        registerSubscription(SingleGameChooserModelCreator().getDifficultyAndCategory()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    view?.loadUserSettings(it)
                }, {
                    it.printStackTrace()
                }))
    }

    fun onDifficultyItemClick(difficulty: String) {
        registerSubscription(SingleGameChooserModelCreator().updateDifficulty(difficulty)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe()
        )
    }

    fun onCategoryItemClick(category: String) {
        registerSubscription(SingleGameChooserModelCreator().updateCategory(category)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe()
        )
    }

    fun onStartGameButtonClick() {
        view?.startGameActivity()
    }
}