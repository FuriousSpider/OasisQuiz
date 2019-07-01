package com.furiousspider.oasisquiz.ui.views

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.furiousspider.oasisquiz.R
import kotlinx.android.synthetic.main.dialog_return.*

class ReturnDialog(context: Context,
                   private val onReturnClickListener: (() -> Unit)? = null) {

    private var dialog: Dialog = Dialog(context)

    init {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_return)

        dialog.dialogReturnOkButton?.setOnClickListener {
            dismiss()
            onReturnClickListener?.invoke()
        }
        dialog.dialogReturnCancelButton?.setOnClickListener {
            dismiss()
        }
    }

    fun show() {
        dialog.show()
    }

    private fun dismiss() {
        dialog.dismiss()
    }
}