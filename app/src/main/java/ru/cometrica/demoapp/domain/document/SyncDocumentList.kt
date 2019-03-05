package ru.cometrica.demoapp.domain.document

import io.reactivex.Completable
import ru.cometrica.demoapp.data.repository.DocumentsRepository
import ru.cometrica.demoapp.domain.InteractorParametrized

/**
 * Update cache by syncing with cloud service.
 */
class SyncDocumentList(private val repository: DocumentsRepository) :
    InteractorParametrized<Long, Completable> {

    override fun build(param: Long): Completable =
        repository.syncDocuments(param)

}
