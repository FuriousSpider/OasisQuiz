package com.furiousspider.oasisquiz.ui.views.customSpinner

import com.furiousspider.oasisquiz.ui.base.BaseRecyclerViewAdapter

class CustomSpinnerRecyclerAdapter(private val onItemClick: (item: String) -> Unit) : BaseRecyclerViewAdapter() {

    fun loadItems(items: List<String>) {
        items.forEach {
            addItem(CustomSpinnerRecyclerItem(it, onItemClick))
        }
        notifyDataSetChanged()
    }
}