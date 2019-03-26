package ru.cometrica.demoapp.presentation.common

import androidx.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<V : MvpView> :
    MvpPresenter<V> {

    protected var view: V? = null

    protected val compositeDisposable = CompositeDisposable()

    @CallSuper
    override fun onAttachView(view: V) {
        this.view = view
    }

    @CallSuper
    override fun onDetachView() {
        compositeDisposable.clear()
        this.view = null
    }

    override fun getViewOrThrow() = view ?: throw IllegalStateException("MvpPresenter must be attached")

}
