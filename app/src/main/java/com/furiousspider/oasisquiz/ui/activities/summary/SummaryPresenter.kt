package com.furiousspider.oasisquiz.ui.activities.summary

import com.furiousspider.oasisquiz.ui.base.BasePresenter

class SummaryPresenter(view: SummaryActivity) : BasePresenter<SummaryActivity>(view) {

    override fun onCreate() {
        view?.init()
        view?.renderData()
    }

    fun onPlayAgainClick() {
        view?.startAnotherGame()
    }

    fun onExitClick() {
        view?.goToMenu()
    }
}