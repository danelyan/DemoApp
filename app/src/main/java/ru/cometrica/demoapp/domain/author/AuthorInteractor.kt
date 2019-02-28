package ru.cometrica.demoapp.domain.author

import io.reactivex.Completable
import io.reactivex.Maybe

class AuthorInteractor {

    fun getCurrentAuthorId(): Maybe<Long> = Maybe.just(1)

    fun setCurrentAuthorId(authorId: Long): Completable = Completable.complete()

}
