package ru.cometrica.demoapp.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import ru.cometrica.demoapp.data.repository.DocumentsRepository
import ru.cometrica.demoapp.data.repository.DocumentsRepositoryImpl
import ru.cometrica.demoapp.device.LocationManagerImpl
import ru.cometrica.demoapp.domain.LocationManager
import ru.cometrica.demoapp.domain.author.GetCurrentAuthor
import ru.cometrica.demoapp.domain.document.StreamDocumentList
import ru.cometrica.demoapp.domain.document.SyncDocumentList
import ru.cometrica.demoapp.domain.location.StreamCurrentLocation
import ru.cometrica.demoapp.presentation.document.presenter.DocumentListPresenter

val appModule = module {

    // Presenters
    factory { DocumentListPresenter(get(), get(), get(), get()) }

    // Interactors
    single { StreamDocumentList(get()) }
    single { SyncDocumentList(get()) }
    single { StreamCurrentLocation(get()) }
    single { GetCurrentAuthor() }

    // Repositories
    single<DocumentsRepository>(createdAtStart = true) { DocumentsRepositoryImpl() }
    single<LocationManager>(createdAtStart = true) { LocationManagerImpl(androidApplication()) }

}
