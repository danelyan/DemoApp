package ru.cometrica.demoapp.domain.document

import io.reactivex.Observable
import ru.cometrica.demoapp.domain.Document
import ru.cometrica.demoapp.repository.DocumentsRepository

class StreamDocumentListInteractor(private val repository: DocumentsRepository) {

    fun streamDocumentList(authorId: Long): Observable<List<Document>> =
        repository.streamDocuments(authorId)
}

