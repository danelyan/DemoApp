package ru.cometrica.demoapp

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.cometrica.demoapp.data.cloud.di.cloudModule
import timber.log.Timber

class DemoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initKoin()
        initJSR310Backport()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@DemoApp)
            modules(appModule, cloudModule)
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initJSR310Backport() {
        // load timezones for JSR310
        AndroidThreeTen.init(this)
    }

}
