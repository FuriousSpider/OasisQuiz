package com.furiousspider.oasisquiz.ui.activities.splash

import com.furiousspider.oasisquiz.interactor.QuestionInteractor
import com.furiousspider.oasisquiz.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SplashPresenter(view: SplashActivity) : BasePresenter<SplashActivity>(view) {

    override fun onCreate() {
        view?.initRealm()
        updateQuestions()
    }

    private fun updateQuestions() {
        registerSubscription(QuestionInteractor().update(view!!)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    view?.startMenuActivity()
                }, {
                    it.printStackTrace()
                    view?.startMenuActivity()
                })
        )
    }
}