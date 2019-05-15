package com.furiousspider.oasisquiz.ui.activities.splash

import android.os.Bundle
import com.furiousspider.oasisquiz.R
import com.furiousspider.oasisquiz.realm.RealmProvider
import com.furiousspider.oasisquiz.ui.base.BaseActivity
import com.furiousspider.oasisquiz.utils.Router

class SplashActivity : BaseActivity<SplashPresenter>() {

    override fun createPresenter() = SplashPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        RealmProvider.init(applicationContext)
    }

    fun startMenuActivity() {
        Router.startMenuActivity(this)
    }
}