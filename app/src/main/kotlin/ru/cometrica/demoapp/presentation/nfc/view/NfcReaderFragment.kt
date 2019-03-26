package ru.cometrica.demoapp.presentation.nfc.view

import android.os.Bundle
import android.view.View
import androidx.annotation.ContentView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import ru.cometrica.demoapp.R
import ru.cometrica.demoapp.presentation.common.BaseFragment
import ru.cometrica.demoapp.presentation.nfc.NfcReaderContract
import ru.cometrica.demoapp.presentation.nfc.model.NfcReaderViewModel

/**
 * TODO: description here
 */
@ContentView(R.layout.fragment_nfc_reader)
class NfcReaderFragment : BaseFragment(), NfcReaderContract.View {

    private val presenter: NfcReaderContract.Presenter
            by inject(parameters = { parametersOf(nfcreaderId) })

    private val nfcreaderId: Int
        get() = (arguments?.getInt(ARG_NFCREADER_ID))
            ?: throw IllegalStateException("ARG_NFCREADER_ID must not be null")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttachView(this)
    }

    //region NfcReaderContract.View

    override fun showNfcReader(item: NfcReaderViewModel) {
        //TODO: code here
    }

    //endregion


    //region Internal

    //endregion

    companion object {

        private const val ARG_NFCREADER_ID = "nfcreaderId"

    }

}
