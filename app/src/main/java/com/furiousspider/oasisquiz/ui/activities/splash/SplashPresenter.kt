package com.furiousspider.oasisquiz.ui.activities.splash

import com.furiousspider.oasisquiz.ui.base.BasePresenter

class SplashPresenter(view: SplashActivity) : BasePresenter<SplashActivity>(view) {

    override fun onCreate() {
        view.startMenuActivity()
    }
}