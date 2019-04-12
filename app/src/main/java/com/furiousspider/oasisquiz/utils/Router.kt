package com.furiousspider.oasisquiz.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.furiousspider.oasisquiz.ui.activities.menu.MenuActivity

object Router {

    fun startMenuActivity(activity: AppCompatActivity) {
        val intent = Intent(activity, MenuActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        activity.startActivity(intent)
    }
}