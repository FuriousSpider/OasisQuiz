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
        state.questionTime = System.currentTimeMillis() - state.questionTime
        state.score += ((1 - (state.questionTime.toDouble() / (state.maxTime * 1000))) * 1000).toInt()
        view?.setScore(++state.score)
        goToNextScreen()
    }

    fun onIncorrectAnswerClick() {
        state.questionTime = System.currentTimeMillis() - state.questionTime
        goToNextScreen()
    }

    fun onAnswerTimeout() {
        state.questionTime = System.currentTimeMillis() - state.questionTime
        goToNextScreen()
    }

    private fun goToNextScreen() {
        view?.hideKeyboard()
        state.time += state.questionTime
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
        view?.hideKeyboard()
        stopCountDownTimer()
        view?.goToMenu()
    }

    fun resetCountDownTimer() {
        state.countDownTimer?.cancel()
        state.countDownTimer = object : CountDownTimer(state.maxTime * 1000L, 500) {
            override fun onFinish() {
                onAnswerTimeout()
            }

            override fun onTick(millisUntilFinished: Long) {
                view?.updateCountDownTimer(millisUntilFinished / 1000)
            }
        }.start()

        state.questionTime = System.currentTimeMillis()
    }

    private fun stopCountDownTimer() {
        state.countDownTimer?.cancel()
    }
}