package com.furiousspider.oasisquiz.ui.views.customSpinner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.furiousspider.oasisquiz.R
import com.furiousspider.oasisquiz.ui.base.RecyclerViewItem
import kotlinx.android.synthetic.main.item_spinner.view.*

class CustomSpinnerRecyclerItem(private val item: String, private val onItemClick: (item: String) -> Unit) : RecyclerViewItem() {
    override val viewType: Int
        get() = R.layout.item_spinner

    override fun getViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    override fun bind(holder: RecyclerView.ViewHolder) {
        val viewHolder = holder as ViewHolder
        viewHolder.textView.text = item
        viewHolder.itemView.setOnClickListener { onItemClick.invoke(item) }
    }

    private class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.itemSpinnerText
    }
}