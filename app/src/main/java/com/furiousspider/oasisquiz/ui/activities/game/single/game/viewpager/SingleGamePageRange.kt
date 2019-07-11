package com.furiousspider.oasisquiz.ui.activities.game.single.game.viewpager

import android.view.View
import com.furiousspider.oasisquiz.R
import com.furiousspider.oasisquiz.ui.activities.game.single.game.model.SingleGameModel
import com.furiousspider.oasisquiz.ui.base.PageItem
import kotlinx.android.synthetic.main.item_game_single_range.view.*

class SingleGamePageRange(private val model: SingleGameModel) : PageItem() {
    var onCorrectAnswer: (() -> Unit)? = null
    var onIncorrectAnswer: (() -> Unit)? = null

    override val layoutId: Int
        get() = R.layout.item_game_single_range

    override fun bindView(itemView: View) {
        itemView.itemGameSingleRangeQuestion.text = model.question

        itemView.itemGameSingleRangeAnswer.setOnEditorActionListener { _, _, _ ->
            itemView.itemGameSingleRangeAnswerButton.performClick()
            true
        }

        itemView.itemGameSingleRangeAnswerButton.setOnClickListener {
            val answer = itemView.itemGameSingleRangeAnswer.text.toString()
            if (answer == model.correctAnswer) {
                onCorrectAnswer?.invoke()
            } else {
                onIncorrectAnswer?.invoke()
            }
        }

        itemView.itemGameSingleRangeClear.setOnClickListener {
            itemView.itemGameSingleRangeAnswer.text.clear()
        }
    }
}