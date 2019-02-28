package ru.cometrica.demoapp.repository

import io.reactivex.Completable
import io.reactivex.subjects.BehaviorSubject
import ru.cometrica.demoapp.domain.Author
import ru.cometrica.demoapp.domain.Document

class DocumentsRepository {

    private val dataCache: BehaviorSubject<List<Document>> =
        BehaviorSubject.create<List<Document>>()

    fun streamDocuments(authorId: Long) =
        dataCache.hide()//.map { list -> list.filter { it.author.authorId == authorId } }


    fun syncDocuments(authorId: Long) =
        Completable.fromCallable {
            dataCache.onNext(
                listOf(
                    Document(1L, "name", "path", Author(10L, "Anton", "Berg")),
                    Document(2L, "name", "path", Author(10L, "Anton", "Berg")),
                    Document(3L, "name", "path", Author(10L, "Anton", "Berg")),
                    Document(4L, "name", "path", Author(10L, "Anton", "Berg"))
                )
            )
        }

}
