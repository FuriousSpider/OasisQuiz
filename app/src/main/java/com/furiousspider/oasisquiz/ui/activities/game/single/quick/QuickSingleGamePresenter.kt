package com.furiousspider.oasisquiz.ui.activities.game.single.quick

import com.furiousspider.oasisquiz.ui.activities.game.single.quick.model.QuickSingleGameModelCreator
import com.furiousspider.oasisquiz.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class QuickSingleGamePresenter(view: QuickSingleGameActivity) : BasePresenter<QuickSingleGameActivity>(view) {

    private val state = QuickSingleGameState()

    override fun onCreate() {
        view?.setScore(state.score)
        loadData()
    }

    private fun loadData() {
        registerSubscription(QuickSingleGameModelCreator().create()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                view?.loadItems(it)
            }, {
                view?.showError("QuickSingleGamePresenter - loadData - error loading question")
                it.printStackTrace()
            })
        )
    }

    fun onCorrectAnswerClick() {
        view?.setScore(++state.score)
        goToNextScreen()
    }

    fun goToNextScreen() {
        view?.let {
            if (it.isItemLast()) {
                view?.showSummaryScreen(state.score, state.time)
            } else {
                view?.goToNextQuestion()
            }
        }
    }

    fun goToMenu() {
        view?.goToMenu()
    }
}