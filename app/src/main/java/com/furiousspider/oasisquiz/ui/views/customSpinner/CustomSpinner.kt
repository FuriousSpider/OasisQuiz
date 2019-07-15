package com.furiousspider.oasisquiz.ui.views.customSpinner

import android.app.Dialog
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.Window
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.furiousspider.oasisquiz.R
import kotlinx.android.synthetic.main.dialog_spinner.*
import kotlinx.android.synthetic.main.view_spinner.view.*

class CustomSpinner @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0) : ConstraintLayout(context, attrs, defStyle) {

    private val items = ArrayList<String>()
    private var dialog = Dialog(context)
    private lateinit var adapter: CustomSpinnerRecyclerAdapter

    var onItemClick: ((String) -> Unit)? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_spinner, this, true)

        initListeners()
        initDialog()
    }

    private fun initListeners() {
        viewSpinnerText.setOnClickListener { showItemsDialog() }
        viewSpinnerImage.setOnClickListener { showItemsDialog() }
    }

    private fun initDialog() {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_spinner)

        dialog.dialogSpinnerRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    fun loadItems(newItems: List<String>) {
        items.clear()
        items.addAll(newItems)

        adapter = CustomSpinnerRecyclerAdapter(this::onItemClick)
        adapter.loadItems(items)
        dialog.dialogSpinnerRecyclerView.adapter = adapter
    }

    fun selectItem(itemText: String?) {
        itemText?.let {
            if (items.contains(itemText)) {
                viewSpinnerText.text = it
            } else {
                viewSpinnerText.text = items.first()
            }
        } ?: run {
            viewSpinnerText.text = items.first()
        }
    }

    private fun showItemsDialog() {
        dialog.show()
    }

    private fun onItemClick(text: String) {
        viewSpinnerText.text = text
        dialog.dismiss()
        onItemClick?.invoke(text)
    }
}