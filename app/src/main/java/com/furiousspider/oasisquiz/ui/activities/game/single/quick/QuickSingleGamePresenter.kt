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
        state.time += System.currentTimeMillis() - state.questionStartTime
        view?.let {
            if (it.isItemLast()) {
                stopCountDownTimer()
                view?.showSummaryScreen(state.score, state.time / 1000.0)
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
        state.countDownTimer = object : CountDownTimer(state.maxTime * 1000L, 500) {
            override fun onFinish() {
                goToNextScreen()
            }

            override fun onTick(millisUntilFinished: Long) {
                view?.updateCountDownTimer(millisUntilFinished / 1000)
            }
        }.start()

        state.questionStartTime = System.currentTimeMillis()
    }

    private fun stopCountDownTimer() {
        state.countDownTimer?.cancel()
    }
}