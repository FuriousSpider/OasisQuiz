package com.furiousspider.oasisquiz.ui.activities.menu

import android.os.Bundle
import com.furiousspider.oasisquiz.R
import com.furiousspider.oasisquiz.ui.base.BaseActivity
import com.furiousspider.oasisquiz.utils.Router
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : BaseActivity<MenuPresenter>() {

    override fun createPresenter() = MenuPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    fun initListeners() {
        activityMenuStartButton.setOnClickListener {
            presenter.startQuickSingleGameActivity()
        }
    }

    fun startQuickSingleGameActivity() {
        Router.startQuickSingleGameActivity(this)
    }
}