package ru.cometrica.demoapp.device

import io.reactivex.Observable
import ru.cometrica.demoapp.domain.LocationManager
import ru.cometrica.demoapp.domain.model.Address
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class LocationManagerImpl : LocationManager {

    private val random = Random(RANDOM_SEED)
    private val hotLocation = Observable.timer(LOCATION_DELAY, TimeUnit.SECONDS)
        .map {
            Address(
                "Address " + random.nextInt(),
                random.nextDouble(),
                random.nextDouble()
            )
        }
        .publish()

    override fun streamLocation(): Observable<Address> = hotLocation.autoConnect()

    companion object {
        const val LOCATION_DELAY = 5L
        const val RANDOM_SEED = 42
    }
}
