package com.furiousspider.oasisquiz.ui.activities.game.single.quick

import android.os.CountDownTimer
import com.furiousspider.oasisquiz.ui.activities.game.single.quick.model.QuickSingleGameModelCreator
import com.furiousspider.oasisquiz.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class QuickSingleGamePresenter(view: QuickSingleGameActivity) : BasePresenter<QuickSingleGameActivity>(view) {

    private val state = QuickSingleGameState()

    override fun onCreate() {
        view?.setScore(state.score)
        loadData()
        resetCountDownTimer()
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
                stopCountDownTimer()
                view?.showSummaryScreen(state.score, state.time)
            } else {
                view?.goToNextQuestion()
            }
        }
    }

    fun goToMenu() {
        stopCountDownTimer()
        view?.goToMenu()
    }

    fun resetCountDownTimer() {
        state.countDownTimer?.cancel()
        state.countDownTimer = object : CountDownTimer(state.maxTime * 1000L, (1000 * state.timeDivider).toLong()) {
            override fun onFinish() {
                goToNextScreen()
            }
            override fun onTick(millisUntilFinished: Long) {
                state.time += state.timeDivider
                view?.updateCountDownTimer(millisUntilFinished / 1000)
            }
        }.start()
    }

    private fun stopCountDownTimer() {
        state.countDownTimer?.cancel()
    }
}