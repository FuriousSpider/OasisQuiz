package com.furiousspider.oasisquiz.ui.activities.game.single.quick

import android.os.Bundle
import com.furiousspider.oasisquiz.R
import com.furiousspider.oasisquiz.ui.activities.game.single.quick.model.QuickSingleGameModel
import com.furiousspider.oasisquiz.ui.activities.game.single.quick.viewpager.QuickSingleGamePagerAdapter
import com.furiousspider.oasisquiz.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_game_single_quick.*

class QuickSingleGameActivity : BaseActivity<QuickSingleGamePresenter>() {
    override fun createPresenter() = QuickSingleGamePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_single_quick)
    }

    fun loadItems(items: List<QuickSingleGameModel>) {
        val pagerAdapter = QuickSingleGamePagerAdapter(this)
        pagerAdapter.loadItems(items)
        pagerAdapter.onCorrectButtonClick = this::onCorrectAnswerClick
        pagerAdapter.onIncorrectButtonClick = this::onIncorrectAnswerClick
        activityGameSingleQuickViewPager.adapter = pagerAdapter
    }

    private fun onCorrectAnswerClick() {
        //TODO: implement
        showError("Correct")
    }

    private fun onIncorrectAnswerClick() {
        //TODO: implement
        showError("Incorrect")
    }
}