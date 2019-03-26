package ru.cometrica.demoapp.presentation.main.view

import android.os.Bundle
import android.view.View
import androidx.annotation.ContentView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import ru.cometrica.demoapp.R
import ru.cometrica.demoapp.presentation.common.BaseFragment
import ru.cometrica.demoapp.presentation.main.MainContract
import ru.cometrica.demoapp.presentation.main.model.MainViewModel

/**
 * TODO: description here
 */
@ContentView(R.layout.fragment_main)
class MainFragment : BaseFragment(), MainContract.View {

    private val presenter: MainContract.Presenter
            by inject(parameters = { parametersOf(mainId) })

    private val mainId: Int
        get() = (arguments?.getInt(ARG_MAIN_ID)) ?: throw IllegalStateException("ARG_MAIN_ID must not be null")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttachView(this)
    }

    //region MainContract.View

    override fun showMain(item: MainViewModel) {
        //TODO: code here
    }

    //endregion


    //region Internal

    //endregion

    companion object {

        private const val ARG_MAIN_ID = "mainId"

    }

}
