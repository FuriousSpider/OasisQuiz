package com.furiousspider.oasisquiz.ui.base

abstract class BasePresenter<V : BaseView>(protected var view: V) : Presenter {

}