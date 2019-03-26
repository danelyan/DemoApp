package ru.cometrica.demoapp.presentation.common

interface MvpPresenter<V : MvpView> {

    fun onAttachView(view: V)

    fun onDetachView()

    fun getViewOrThrow(): V
}