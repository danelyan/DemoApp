package ru.cometrica.demoapp.domain.author

import io.reactivex.Completable
import ru.cometrica.demoapp.domain.InteractorParametrized

class SetCurrentAuthorId : InteractorParametrized<Long, Completable> {

    override fun build(param: Long) = Completable.complete()

}
