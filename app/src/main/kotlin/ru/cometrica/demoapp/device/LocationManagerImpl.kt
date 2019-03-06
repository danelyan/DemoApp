package ru.cometrica.demoapp.device

import android.annotation.SuppressLint
import android.content.Context
import io.reactivex.Observable
import ru.cometrica.demoapp.domain.Address
import ru.cometrica.demoapp.domain.LocationManager
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class LocationManagerImpl(context: Context) : LocationManager {

    private val random = Random(RANDOM_SEED)
    private val hotLocation = Observable.timer(LOCATION_DELAY, TimeUnit.SECONDS)
        .map { Address("Address " + random.nextInt(), random.nextDouble(), random.nextDouble()) }
        .publish()

    @SuppressLint("MissingPermission") // FIXME properly request permission
    override fun streamLocation(): Observable<Address> = hotLocation.autoConnect()

    companion object {
        const val LOCATION_DELAY = 5L
        const val RANDOM_SEED = 42
    }
}
