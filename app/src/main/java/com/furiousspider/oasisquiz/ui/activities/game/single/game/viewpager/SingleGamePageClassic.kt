package com.furiousspider.oasisquiz.ui.activities.game.single.game.viewpager

import android.view.View
import android.widget.TextView
import com.furiousspider.oasisquiz.R
import com.furiousspider.oasisquiz.ui.activities.game.single.game.model.SingleGameModel
import com.furiousspider.oasisquiz.ui.base.PageItem
import kotlinx.android.synthetic.main.item_game_single_classic.view.*
import kotlin.random.Random

class SingleGamePageClassic(private val model: SingleGameModel) : PageItem() {
    var onCorrectButtonClick: (() -> Unit)? = null
    var onIncorrectButtonClick: (() -> Unit)? = null

    private var correctAnswerPosition = 0
    private val buttonList = ArrayList<TextView>()

    override val layoutId: Int
        get() = R.layout.item_game_single_classic

    override fun bindView(itemView: View) {
        itemView.itemGameSingleClassicQuestion.text = model.question

        correctAnswerPosition = Random.nextInt(0, 4)
        buttonList.add(itemView.itemGameSingleClassicAnswer1)
        buttonList.add(itemView.itemGameSingleClassicAnswer2)
        buttonList.add(itemView.itemGameSingleClassicAnswer3)
        buttonList.add(itemView.itemGameSingleClassicAnswer4)
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

        buttonList.forEach {
            it.setOnClickListener { button ->
                if (buttonList.indexOf(button) == correctAnswerPosition) {
                    onCorrectButtonClick?.invoke()
                } else {
                    onIncorrectButtonClick?.invoke()
                }
            }
        }
    }
}