package com.furiousspider.oasisquiz.ui.activities.summary

import android.os.Bundle
import android.view.View
import com.furiousspider.oasisquiz.R
import com.furiousspider.oasisquiz.ui.base.BaseActivity
import com.furiousspider.oasisquiz.utils.Router
import kotlinx.android.synthetic.main.activity_summary.*
import java.text.DecimalFormat

class SummaryActivity : BaseActivity<SummaryPresenter>() {

    companion object {
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
        val time = intent?.extras?.getInt(EXTRA_TIME, -1)

        activitySummaryScore.text = getString(R.string.activity_summary_score, score)
        time?.let {
            if (it != -1) {
                activitySummaryTime.text = getString(R.string.activity_summary_time, DecimalFormat("##.##").format( time / 1000.0 ))
                activitySummaryTime.visibility = View.VISIBLE
            } else {
                activitySummaryTime.visibility = View.GONE
            }
        } ?: run {
            activitySummaryTime.visibility = View.GONE
        }
    }

    fun startAnotherGame() {
        //TODO: implement checking what kind of game was played to start again the right one
        Router.startQuickSingleGameActivity(this)
    }

    fun goToMenu() {
        Router.startMenuActivity(this)
    }

    override fun onBackPressed() {
        Router.startMenuActivity(this)
    }
}