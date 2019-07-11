package com.furiousspider.oasisquiz.ui.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

open class BaseRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    protected val items = ArrayList<RecyclerViewItem>()

    override fun getItemViewType(position: Int): Int = items[position].viewType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            items.first { it.viewType == viewType }.getViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        items[position].bind(holder)
    }

    fun addItem(newItem: RecyclerViewItem) {
        items.add(newItem)
    }
}