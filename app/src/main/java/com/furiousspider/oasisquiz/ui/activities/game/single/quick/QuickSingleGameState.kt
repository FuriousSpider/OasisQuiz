package com.furiousspider.oasisquiz.ui.activities.game.single.quick

import android.os.CountDownTimer

data class QuickSingleGameState(
    var score: Int = 0,
    var time: Double = 0.0,
    val maxTime: Int = 15,
    val timeDivider: Double = 0.05,
    var countDownTimer: CountDownTimer? = null
)