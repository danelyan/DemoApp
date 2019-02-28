package ru.cometrica.demoapp.domain.location

import ru.cometrica.demoapp.domain.ILocationManager

class CurrentLocationInteractor(val location: ILocationManager) {

    fun streamLocation() = location.streamLocation()

}
