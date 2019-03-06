package ru.cometrica.demoapp.presentation.document.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_document_list.*
import kotlinx.android.synthetic.main.fragment_document_list.view.*
import org.koin.android.ext.android.inject
import ru.cometrica.demoapp.R
import ru.cometrica.demoapp.presentation.document.model.DocumentViewModel
import ru.cometrica.demoapp.presentation.document.presenter.DocumentListPresenter

class DocumentListFragment : Fragment(), DocumentListView {

    private val presenter: DocumentListPresenter by inject()

    private val columnCount by lazy {
        arguments?.getInt(ARG_COLUMN_COUNT, DEFAULT_COLUMN_COUNT) ?: DEFAULT_COLUMN_COUNT
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_document_list, container, false)
        view.listDocuments.adapter = DocumentListRecyclerViewAdapter()
        view.listDocuments.layoutManager = when {
            columnCount <= 1 -> LinearLayoutManager(context)
            else -> GridLayoutManager(context, columnCount)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttachView(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDetachView()
    }

    override fun showDocuments(items: List<DocumentViewModel>) {
        (view?.listDocuments?.adapter as DocumentListRecyclerViewAdapter).data = items
    }

    override fun showDocumentListError(it: Throwable?) {
        Toast.makeText(context, "DOCUMENT LIST ERROR", Toast.LENGTH_SHORT).show()
    }

    override fun showAuthorIdError() {
        Toast.makeText(context, "AUTHOR ID ERROR", Toast.LENGTH_SHORT).show()
    }

    override fun showSomeError() {
        Toast.makeText(context, "SOME ERROR", Toast.LENGTH_SHORT).show()
    }

    override fun refreshClick(): Observable<Unit> =
        btnSync.clicks()

    override fun authorIdFieldChange(): Observable<String> =
        editSearch.textChanges().map { it.toString() }

    override fun showAddress(it: String) {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"
        const val DEFAULT_COLUMN_COUNT = 1

        @JvmStatic
        fun newInstance(columnCount: Int) =
            DocumentListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }


}
