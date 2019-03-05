package ru.cometrica.demoapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.cometrica.demoapp.di.appModule

class DemoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@DemoApp)
            modules(appModule)
            androidLogger()
        }

    }
}
