package ru.cometrica.demoapp.domain

import io.reactivex.Observable
import ru.cometrica.demoapp.domain.model.Address

interface LocationManager {

    fun streamLocation(): Observable<Address>
}
