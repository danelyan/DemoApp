package ru.cometrica.demoapp.presentation.document.view

import io.reactivex.Observable
import ru.cometrica.demoapp.presentation.document.model.DocumentViewModel

interface IDocumentListView {
    fun showDocuments(items: List<DocumentViewModel>)
    fun showDocumentListError(it: Throwable?)
    fun showAuthorIdError()
    fun showSomeError()
    fun refreshClick(): Observable<Unit>
    fun authorIdFieldChange(): Observable<String>
    fun showAddress(it: String)
}
