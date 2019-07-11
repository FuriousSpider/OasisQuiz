package com.furiousspider.oasisquiz.ui.activities.game.single.game.viewpager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.furiousspider.oasisquiz.ui.activities.game.single.game.model.SingleGameModel
import com.furiousspider.oasisquiz.ui.base.PageItem
import com.furiousspider.oasisquiz.utils.QuestionType

class SingleGamePagerAdapter(private val context: Context) : PagerAdapter() {

    var onCorrectButtonClick: (() -> Unit)? = null
    var onIncorrectButtonClick: (() -> Unit)? = null

    private var items: ArrayList<SingleGameModel> = ArrayList()

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = items.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val page: PageItem
        when (items[position].type) {
            QuestionType.CLASSIC -> {
                val newPage = SingleGamePageClassic(items[position])
                onCorrectButtonClick?.let {
                    newPage.onCorrectButtonClick = it
                }
                onIncorrectButtonClick?.let {
                    newPage.onIncorrectButtonClick = it
                }
                page = newPage
            }
            QuestionType.RANGE -> {
                val newPage = SingleGamePageRange(items[position])
                onCorrectButtonClick?.let {
                    newPage.onCorrectAnswer = it
                }
                onIncorrectButtonClick?.let {
                    newPage.onIncorrectAnswer = it
                }
                page = newPage
            }
        }

        val layout = LayoutInflater.from(context).inflate(page.layoutId, container, false)
        page.bindView(layout)
        container.addView(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    fun loadItems(items: List<SingleGameModel>) {
        this.items.addAll(items)
    }
}