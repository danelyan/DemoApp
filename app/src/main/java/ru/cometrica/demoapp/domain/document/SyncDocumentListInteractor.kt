package ru.cometrica.demoapp.domain.document

import io.reactivex.Completable
import ru.cometrica.demoapp.repository.DocumentsRepository

class SyncDocumentListInteractor(private val repository: DocumentsRepository) {

    fun syncDocumentList(authorId: Long): Completable =
        repository.syncDocuments(authorId)
}
