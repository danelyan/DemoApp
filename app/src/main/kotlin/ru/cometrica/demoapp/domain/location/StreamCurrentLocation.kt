package ru.cometrica.demoapp.domain.location

import io.reactivex.Observable
import ru.cometrica.demoapp.domain.Address
import ru.cometrica.demoapp.domain.Interactor
import ru.cometrica.demoapp.domain.LocationManager

/**
 * Stream current location.
 */
class StreamCurrentLocation(private val location: LocationManager) : Interactor<Observable<Address>> {

    override fun build() =
        location.streamLocation()

}
