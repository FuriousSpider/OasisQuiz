package com.furiousspider.oasisquiz.ui.activities.game.single.quick.viewpager

import android.view.View
import android.widget.Button
import com.furiousspider.oasisquiz.R
import com.furiousspider.oasisquiz.ui.activities.game.single.quick.model.QuickSingleGameModel
import com.furiousspider.oasisquiz.ui.base.PageItem
import kotlinx.android.synthetic.main.item_game_single_quick.view.*
import kotlin.random.Random

class QuickSingleGamePage(private val model: QuickSingleGameModel) : PageItem() {
    var onCorrectButtonClick: (() -> Unit)? = null
    var onIncorrectButtonClick: (() -> Unit)? = null

    private var correctAnswerPosition = 0
    private val buttonList = ArrayList<Button>()

    override val layoutId: Int
        get() = R.layout.item_game_single_quick

    override fun bindView(itemView: View) {
        itemView.itemGameSingleQuickQuestion.text = model.question

        correctAnswerPosition = Random.nextInt(0, 4)
        buttonList.add(itemView.itemGameSingleQuickAnswer1)
        buttonList.add(itemView.itemGameSingleQuickAnswer2)
        buttonList.add(itemView.itemGameSingleQuickAnswer3)
        buttonList.add(itemView.itemGameSingleQuickAnswer4)
        var shift = false
        for (i in 0..3) {
            when {
                i == correctAnswerPosition -> {
                    buttonList[i].text = model.correctAnswer
                    shift = true
                }
                shift -> buttonList[i].text = model.incorrectAnswers[i - 1]
                else -> buttonList[i].text = model.incorrectAnswers[i]
            }
        }

        buttonList.forEach { it.setOnClickListener {button ->
            if (buttonList.indexOf(button) == correctAnswerPosition) {
                onCorrectButtonClick?.invoke()
            } else {
                onIncorrectButtonClick?.invoke()
            }
        } }
    }
}