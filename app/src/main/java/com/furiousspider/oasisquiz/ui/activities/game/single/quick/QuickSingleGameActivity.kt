package com.furiousspider.oasisquiz.ui.activities.game.single.quick

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.furiousspider.oasisquiz.R
import com.furiousspider.oasisquiz.ui.activities.game.single.quick.model.QuickSingleGameModel
import com.furiousspider.oasisquiz.ui.activities.game.single.quick.viewpager.QuickSingleGamePagerAdapter
import com.furiousspider.oasisquiz.ui.base.BaseActivity
import com.furiousspider.oasisquiz.ui.views.ReturnDialog
import com.furiousspider.oasisquiz.utils.Router
import com.furiousspider.oasisquiz.utils.hideKeyboard
import kotlinx.android.synthetic.main.activity_game_single_quick.*

class QuickSingleGameActivity : BaseActivity<QuickSingleGamePresenter>() {
    override fun createPresenter() = QuickSingleGamePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_single_quick)

        activityGameSingleQuickViewPager.clearOnPageChangeListeners()
        activityGameSingleQuickViewPager.addOnPageChangeListener(onPageChangeListener)

        activityGameSingleQuickExitButton.setOnClickListener { onBackPressed() }
    }

    override fun onBackPressed() {
        val dialog = ReturnDialog(this, presenter::goToMenu)
        dialog.show()
    }

    private val onPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {}
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
        override fun onPageSelected(position: Int) {
            presenter.resetCountDownTimer()
        }
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
        presenter.onIncorrectAnswerClick()
    }

    fun setScore(score: Int) {
        activityGameSingleQuickScore.text = getString(R.string.activity_game_single_quick_score_label, score)
    }

    fun isItemLast(): Boolean = activityGameSingleQuickViewPager.isItemLast()

    fun goToNextQuestion() {
        activityGameSingleQuickViewPager.goToNextPage()
    }

    fun showSummaryScreen(score: Int, time: Double) {
        Router.startSummaryActivity(this, score, time)
    }

    fun goToMenu() {
        Router.startMenuActivity(this)
    }

    fun updateCountDownTimer(time: Long) {
        activityGameSingleQuickTimer.text = time.toString()
    }

    fun hideKeyboard() {
        activityGameSingleQuickTimer.hideKeyboard()
    }
}