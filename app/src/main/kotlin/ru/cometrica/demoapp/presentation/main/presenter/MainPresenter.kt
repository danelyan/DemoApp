package ru.cometrica.demoapp.presentation.main.presenter

import ru.cometrica.demoapp.presentation.common.BasePresenter
import ru.cometrica.demoapp.presentation.main.MainContract
import ru.cometrica.demoapp.util.SchedulerProvider

class MainPresenter(
    private val scheduler: SchedulerProvider
) : BasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun onAttachView(view: MainContract.View) {
        super.onAttachView(view)
        // TODO: code here
    }

    override fun onDetachView() {
        super.onDetachView()
        // TODO: code here
    }

}
