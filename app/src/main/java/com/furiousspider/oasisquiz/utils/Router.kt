package com.furiousspider.oasisquiz.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.furiousspider.oasisquiz.ui.activities.game.single.quick.QuickSingleGameActivity
import com.furiousspider.oasisquiz.ui.activities.menu.MenuActivity
import com.furiousspider.oasisquiz.ui.activities.summary.SummaryActivity

object Router {

    fun startMenuActivity(activity: AppCompatActivity) {
        val intent = Intent(activity, MenuActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        activity.startActivity(intent)
    }

    fun startQuickSingleGameActivity(activity: AppCompatActivity) {
        val intent = Intent(activity, QuickSingleGameActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        activity.startActivity(intent)
    }

    fun startSummaryActivity(activity: AppCompatActivity, score: Int? = null, time: Int? = null) {
        val intent = Intent(activity, SummaryActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.putExtra(SummaryActivity.EXTRA_PREVIOUS_GAME, activity::class.java.name)
        score?.let {
            intent.putExtra(SummaryActivity.EXTRA_SCORE, it)
        }
        time?.let {
            intent.putExtra(SummaryActivity.EXTRA_TIME, it)
        }
        activity.startActivity(intent)
    }
}