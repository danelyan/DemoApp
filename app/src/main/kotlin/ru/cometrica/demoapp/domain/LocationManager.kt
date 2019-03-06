package ru.cometrica.demoapp.domain

import io.reactivex.Observable

interface LocationManager {

    fun streamLocation(): Observable<Address>
}
