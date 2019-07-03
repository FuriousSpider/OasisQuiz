package com.furiousspider.oasisquiz.ui.activities.game.single.quick.viewpager

import android.view.View
import com.furiousspider.oasisquiz.R
import com.furiousspider.oasisquiz.ui.activities.game.single.quick.model.QuickSingleGameModel
import com.furiousspider.oasisquiz.ui.base.PageItem
import kotlinx.android.synthetic.main.item_game_single_quick_range.view.*

class QuickSingleGamePageRange(private val model: QuickSingleGameModel) : PageItem() {
    var onCorrectAnswer: (() -> Unit)? = null
    var onIncorrectAnswer: (() -> Unit)? = null

    override val layoutId: Int
        get() = R.layout.item_game_single_quick_range

    override fun bindView(itemView: View) {
        itemView.itemGameSingleQuickRangeQuestion.text = model.question

        itemView.itemGameSingleQuickRangeAnswer.setOnEditorActionListener { _, _, _ ->
            itemView.itemGameSingleQuickRangeAnswerButton.performClick()
            true
        }

        itemView.itemGameSingleQuickRangeAnswerButton.setOnClickListener {
            val answer = itemView.itemGameSingleQuickRangeAnswer.text.toString()
            if (answer == model.correctAnswer) {
                onCorrectAnswer?.invoke()
            } else {
                onIncorrectAnswer?.invoke()
            }
        }

        itemView.itemGameSingleQuickRangeClear.setOnClickListener {
            itemView.itemGameSingleQuickRangeAnswer.text.clear()
        }
    }
}