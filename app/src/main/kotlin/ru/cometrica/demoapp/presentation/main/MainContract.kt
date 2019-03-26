package ru.cometrica.demoapp.presentation.main

import ru.cometrica.demoapp.presentation.common.MvpPresenter
import ru.cometrica.demoapp.presentation.common.MvpView
import ru.cometrica.demoapp.presentation.main.model.MainViewModel

interface MainContract {

    interface View : MvpView {

        fun showMain(item: MainViewModel)

    }

    interface Presenter : MvpPresenter<View> {

        // TODO code here

    }

}
