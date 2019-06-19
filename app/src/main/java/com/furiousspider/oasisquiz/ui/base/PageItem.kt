package com.furiousspider.oasisquiz.ui.base

import android.view.View

abstract class PageItem {
    abstract val layoutId: Int

    abstract fun bindView(itemView: View)
}