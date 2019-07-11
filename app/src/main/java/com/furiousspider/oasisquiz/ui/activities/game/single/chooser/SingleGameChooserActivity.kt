package com.furiousspider.oasisquiz.ui.activities.game.single.chooser

import android.os.Bundle
import com.furiousspider.oasisquiz.R
import com.furiousspider.oasisquiz.ui.base.BaseActivity
import com.furiousspider.oasisquiz.utils.QuestionCategory
import com.furiousspider.oasisquiz.utils.QuestionDifficulty
import com.furiousspider.oasisquiz.utils.Router
import kotlinx.android.synthetic.main.activity_game_single_chooser.*

class SingleGameChooserActivity : BaseActivity<SingleGameChooserPresenter>() {

    override fun createPresenter() = SingleGameChooserPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_single_chooser)
    }

    fun initListeners() {
        activityGameSingleChooserStartButton.setOnClickListener { presenter.onStartGameButtonClick() }
        activityGameSingleChooserDifficulty.onItemClick = { presenter.onDifficultyItemClick(it) }
        activityGameSingleChooserCategory.onItemClick = { presenter.onCategoryItemClick(it) }
    }

    fun loadSpinners() {
        activityGameSingleChooserCategory.loadItems(QuestionCategory.values().map { it.toString() })
        activityGameSingleChooserDifficulty.loadItems(QuestionDifficulty.values().map { it.toString() })
    }

    fun startGameActivity() {
        //TODO:implement filter
        Router.startSingleGameActivity(this)
    }
}