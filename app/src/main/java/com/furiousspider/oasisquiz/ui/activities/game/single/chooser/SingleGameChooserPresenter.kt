package com.furiousspider.oasisquiz.ui.activities.game.single.chooser

import com.furiousspider.oasisquiz.ui.base.BasePresenter

class SingleGameChooserPresenter(view: SingleGameChooserActivity) : BasePresenter<SingleGameChooserActivity>(view) {

    override fun onCreate() {
        view?.initListeners()
        view?.loadSpinners()
    }

    fun onDifficultyItemClick(text: String) {
        //TODO: save to user preferences
        view?.showError(text)
    }

    fun onCategoryItemClick(text: String) {
        //TODO: save to user preferences
        view?.showError(text)
    }

    fun onStartGameButtonClick() {
        view?.startGameActivity()
    }
}