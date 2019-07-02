package com.furiousspider.oasisquiz.ui.activities.summary

import android.os.Bundle
import android.view.View
import com.furiousspider.oasisquiz.R
import com.furiousspider.oasisquiz.ui.activities.game.single.quick.QuickSingleGameActivity
import com.furiousspider.oasisquiz.ui.base.BaseActivity
import com.furiousspider.oasisquiz.utils.Router
import kotlinx.android.synthetic.main.activity_summary.*
import java.text.DecimalFormat

class SummaryActivity : BaseActivity<SummaryPresenter>() {

    companion object {
        const val EXTRA_PREVIOUS_GAME = "EXTRA_PREVIOUS_GAME"
        const val EXTRA_SCORE = "EXTRA_SCORE"
        const val EXTRA_TIME = "EXTRA_TIME"
    }

    override fun createPresenter(): SummaryPresenter = SummaryPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)
    }

    fun init() {
        activitySummaryPlayAgainButton.setOnClickListener {
            presenter.onPlayAgainClick()
        }
        activitySummaryExitButton.setOnClickListener {
            presenter.onExitClick()
        }
    }

    fun renderData() {
        val score = intent?.extras?.getInt(EXTRA_SCORE) ?: 0
        val time = intent?.extras?.getDouble(EXTRA_TIME, -1.0) ?: -1.0

        activitySummaryScore.text = getString(R.string.activity_summary_score, score)
        if (time != -1.0) {
            activitySummaryTime.text = getString(R.string.activity_summary_time, DecimalFormat("##.##").format(time))
            activitySummaryTime.visibility = View.VISIBLE
        } else {
            activitySummaryTime.visibility = View.GONE
        }
    }

    fun startAnotherGame() {
        when (intent?.extras?.getString(EXTRA_PREVIOUS_GAME, "")) {
            QuickSingleGameActivity::class.java.name -> Router.startQuickSingleGameActivity(this)
            else -> Router.startMenuActivity(this)
        }
    }

    fun goToMenu() {
        Router.startMenuActivity(this)
    }

    override fun onBackPressed() {
        Router.startMenuActivity(this)
    }
}