package ru.cometrica.demoapp.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import ru.cometrica.demoapp.data.cache.DummyContent
import ru.cometrica.demoapp.domain.Document

interface DocumentsRepository {
    fun streamDocuments(authorId: Long): Observable<List<Document>>
    fun syncDocuments(authorId: Long): Completable
}

class DocumentsRepositoryImpl : DocumentsRepository {

    private val dataCache: BehaviorSubject<List<Document>> =
        BehaviorSubject.create<List<Document>>()

    override fun streamDocuments(authorId: Long): Observable<List<Document>> =
        dataCache.hide()

    override fun syncDocuments(authorId: Long) =
        Completable.fromCallable { dataCache.onNext(DummyContent.ITEMS) }

}
