package com.furiousspider.oasisquiz.ui.base

import io.reactivex.disposables.Disposable

abstract class BasePresenter<V : BaseView>(protected var view: V?) : Presenter {

    companion object {
        const val DEFAULT_SUBSCRIPTION_TAG = "DEFAULT_SUBSCRIPTION_TAG"
    }

    private val subscriptions = HashMap<String, Disposable>()

    override fun onDestroy() {
        unsubscribeAll()
        view = null
    }

    protected fun registerSubscription(disposable: Disposable, tag: String = DEFAULT_SUBSCRIPTION_TAG) {
        subscriptions.filter { it.key == tag }.forEach {
            subscriptions.remove(it.key)
            unsubscribe(it.value)
        }
        subscriptions[tag] = disposable
    }

    private fun unsubscribeAll() {
        subscriptions.forEach { unsubscribe(it.value) }
        subscriptions.clear()
    }

    private fun unsubscribe(disposable: Disposable?) {
        if (disposable != null && !disposable.isDisposed) {
            disposable.dispose()
        }
    }
}