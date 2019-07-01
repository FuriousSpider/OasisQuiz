package com.furiousspider.oasisquiz.ui.activities.game.single.quick

import android.os.Bundle
import com.furiousspider.oasisquiz.R
import com.furiousspider.oasisquiz.ui.activities.game.single.quick.model.QuickSingleGameModel
import com.furiousspider.oasisquiz.ui.activities.game.single.quick.viewpager.QuickSingleGamePagerAdapter
import com.furiousspider.oasisquiz.ui.base.BaseActivity
import com.furiousspider.oasisquiz.utils.Router
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
        presenter.onCorrectAnswerClick()
    }

    private fun onIncorrectAnswerClick() {
        presenter.goToNextScreen()
    }

    fun setScore(score: Int) {
        activityGameSingleQuickScore.text = getString(R.string.activity_game_single_quick_score_label, score)
    }

    fun isItemLast(): Boolean = activityGameSingleQuickViewPager.isItemLast()

    fun goToNextQuestion() {
        activityGameSingleQuickViewPager.goToNextPage()
    }

    fun showSummaryScreen(score: Int, time: Int) {
        Router.startSummaryActivity(this, score, time)
    }
}