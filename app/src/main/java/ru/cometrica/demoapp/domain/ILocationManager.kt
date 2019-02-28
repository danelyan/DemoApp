package ru.cometrica.demoapp.domain

import io.reactivex.Observable

interface ILocationManager {

    fun streamLocation(): Observable<ru.cometrica.demoapp.domain.Address>
}
