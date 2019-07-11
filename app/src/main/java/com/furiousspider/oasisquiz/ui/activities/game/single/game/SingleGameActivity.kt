package com.furiousspider.oasisquiz.ui.activities.game.single.game

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.furiousspider.oasisquiz.R
import com.furiousspider.oasisquiz.ui.activities.game.single.game.model.SingleGameModel
import com.furiousspider.oasisquiz.ui.activities.game.single.game.viewpager.SingleGamePagerAdapter
import com.furiousspider.oasisquiz.ui.base.BaseActivity
import com.furiousspider.oasisquiz.ui.views.ReturnDialog
import com.furiousspider.oasisquiz.utils.Router
import com.furiousspider.oasisquiz.utils.hideKeyboard
import kotlinx.android.synthetic.main.activity_game_single.*

class SingleGameActivity : BaseActivity<SingleGamePresenter>() {
    companion object {
        //TODO: change max size when more questions available
        const val NUMBER_OF_QUESTIONS = 3
    }

    override fun createPresenter() = SingleGamePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_single)
    }

    fun initListeners() {
        activityGameSingleViewPager.clearOnPageChangeListeners()
        activityGameSingleViewPager.addOnPageChangeListener(onPageChangeListener)

        activityGameSingleExitButton.setOnClickListener { onBackPressed() }
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

    fun loadItems(items: List<SingleGameModel>) {
        val pagerAdapter = SingleGamePagerAdapter(this)
        pagerAdapter.loadItems(items)
        pagerAdapter.onCorrectButtonClick = this::onCorrectAnswerClick
        pagerAdapter.onIncorrectButtonClick = this::onIncorrectAnswerClick
        activityGameSingleViewPager.adapter = pagerAdapter
    }

    private fun onCorrectAnswerClick() {
        presenter.onCorrectAnswerClick()
    }

    private fun onIncorrectAnswerClick() {
        presenter.onIncorrectAnswerClick()
    }

    fun setScore(score: Int) {
        activityGameSingleScore.text = getString(R.string.activity_game_single_score_label, score)
    }

    fun isItemLast(): Boolean = activityGameSingleViewPager.isItemLast()

    fun goToNextQuestion() {
        activityGameSingleViewPager.goToNextPage()
    }

    fun showSummaryScreen(score: Int, time: Double) {
        Router.startSummaryActivity(this, score, time)
    }

    fun goToMenu() {
        Router.startMenuActivity(this)
    }

    fun updateCountDownTimer(time: Long) {
        activityGameSingleTimer.text = time.toString()
    }

    fun hideKeyboard() {
        activityGameSingleTimer.hideKeyboard()
    }
}