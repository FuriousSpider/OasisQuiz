package com.furiousspider.oasisquiz.ui.activities.game.single.quick

import android.os.Bundle
import com.furiousspider.oasisquiz.R
import com.furiousspider.oasisquiz.ui.base.BaseActivity

class QuickSingleGameActivity : BaseActivity<QuickSingleGamePresenter>() {
    override fun createPresenter() = QuickSingleGamePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_single_quick)
    }
}