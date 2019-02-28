package ru.cometrica.demoapp.repository

import io.reactivex.Completable
import io.reactivex.subjects.BehaviorSubject
import ru.cometrica.demoapp.data.DummyContent
import ru.cometrica.demoapp.domain.Document

class DocumentsRepository {

    private val dataCache: BehaviorSubject<List<Document>> =
        BehaviorSubject.create<List<Document>>()

    fun streamDocuments(authorId: Long) =
        dataCache.hide()


    fun syncDocuments(authorId: Long) =
        Completable.fromCallable {
            dataCache.onNext(DummyContent.ITEMS)
        }

}
