package com.furiousspider.oasisquiz.ui.activities.menu

import com.furiousspider.oasisquiz.ui.base.BasePresenter

class MenuPresenter(view: MenuActivity) : BasePresenter<MenuActivity>(view) {
    override fun onCreate() {
        view?.initListeners()
    }

    fun startSingleGameChooserActivity() {
        view?.startSingleGameChooserActivity()
    }
}