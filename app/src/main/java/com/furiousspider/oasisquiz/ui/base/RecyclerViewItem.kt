package com.furiousspider.oasisquiz.ui.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewItem {

    abstract val viewType: Int

    abstract fun getViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    abstract fun bind(holder: RecyclerView.ViewHolder)
}