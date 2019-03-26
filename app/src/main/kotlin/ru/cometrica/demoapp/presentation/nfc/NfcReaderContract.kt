package ru.cometrica.demoapp.presentation.nfc

import ru.cometrica.demoapp.presentation.common.MvpPresenter
import ru.cometrica.demoapp.presentation.common.MvpView
import ru.cometrica.demoapp.presentation.nfc.model.NfcReaderViewModel

interface NfcReaderContract {

    interface View : MvpView {

        fun showNfcReader(item: NfcReaderViewModel)

    }

    interface Presenter : MvpPresenter<View> {

        // TODO code here

    }

}
