package com.furiousspider.oasisquiz.ui.activities.game.single.quick

import android.os.CountDownTimer

data class QuickSingleGameState(
        val maxTime: Int = 20,
        var score: Int = 0,
        var time: Long = 0,
        var questionTime: Long = 0L,
        var countDownTimer: CountDownTimer? = null
)