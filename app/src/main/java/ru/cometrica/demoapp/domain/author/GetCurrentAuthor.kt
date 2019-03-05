package ru.cometrica.demoapp.domain.author

import io.reactivex.Maybe
import ru.cometrica.demoapp.domain.Interactor

class GetCurrentAuthor : Interactor<Maybe<Long>> {

    override fun build() = Maybe.just(1L)

}
