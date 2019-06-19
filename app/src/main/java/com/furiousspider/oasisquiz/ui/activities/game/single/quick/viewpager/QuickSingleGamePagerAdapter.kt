package com.furiousspider.oasisquiz.ui.activities.game.single.quick.viewpager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.furiousspider.oasisquiz.ui.activities.game.single.quick.model.QuickSingleGameModel

class QuickSingleGamePagerAdapter(private val context: Context) : PagerAdapter() {

    var onCorrectButtonClick: (() -> Unit)? = null
    var onIncorrectButtonClick: (() -> Unit)? = null

    private var items: ArrayList<QuickSingleGameModel> = ArrayList()

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = items.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val page = QuickSingleGamePage(items[position])
        onCorrectButtonClick?.let {
            page.onCorrectButtonClick = it
        }
        onIncorrectButtonClick?.let {
            page.onIncorrectButtonClick = it
        }

        val layout = LayoutInflater.from(context).inflate(page.layoutId, container, false)
        page.bindView(layout)
        container.addView(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    fun loadItems(items: List<QuickSingleGameModel>) {
        this.items.addAll(items)
    }
}