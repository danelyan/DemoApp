package ru.cometrica.demoapp.presentation.nfc.presenter

import ru.cometrica.demoapp.presentation.common.BasePresenter
import ru.cometrica.demoapp.presentation.nfc.NfcReaderContract
import ru.cometrica.demoapp.util.SchedulerProvider

class NfcReaderPresenter(
    private val scheduler: SchedulerProvider
) : BasePresenter<NfcReaderContract.View>(), NfcReaderContract.Presenter {

    override fun onAttachView(view: NfcReaderContract.View) {
        super.onAttachView(view)
        // TODO: code here
    }

    override fun onDetachView() {
        super.onDetachView()
        // TODO: code here
    }

}
