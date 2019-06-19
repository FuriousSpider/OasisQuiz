package com.furiousspider.oasisquiz.ui.activities.game.single.quick

import com.furiousspider.oasisquiz.ui.activities.game.single.quick.model.QuickSingleGameModelCreator
import com.furiousspider.oasisquiz.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class QuickSingleGamePresenter(view: QuickSingleGameActivity) : BasePresenter<QuickSingleGameActivity>(view) {

    override fun onCreate() {
        loadData()
    }

    private fun loadData() {
        registerSubscription(QuickSingleGameModelCreator().create()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                view?.loadItems(it)
            },{
                view?.showError("QuickSingleGamePresenter - loadData - error loading question")
                it.printStackTrace()
            }))
    }
}