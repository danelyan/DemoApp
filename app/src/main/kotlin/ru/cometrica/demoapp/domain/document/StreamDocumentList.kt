package ru.cometrica.demoapp.domain.document

import io.reactivex.Observable
import ru.cometrica.demoapp.data.repository.DocumentsRepository
import ru.cometrica.demoapp.domain.model.Document
import ru.cometrica.demoapp.domain.InteractorParametrized

/**
 * Stream list of document from the cache.
 */
class StreamDocumentList(private val repository: DocumentsRepository) :
    InteractorParametrized<Long, Observable<List<Document>>> {

    override fun build(param: Long): Observable<List<Document>> =
        repository.streamDocuments(param)

}
