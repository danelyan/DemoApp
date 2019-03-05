package ru.cometrica.demoapp.presentation

open class BasePresenter<V> {

    protected var view: V? = null

    open fun onAttachView(view: V) {
        this.view = view
    }

    open fun onDetachView() {
        this.view = null
    }

}
