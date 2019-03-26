package ru.cometrica.demoapp

import org.koin.dsl.module
import ru.cometrica.demoapp.data.repository.DocumentsRepository
import ru.cometrica.demoapp.data.repository.DocumentsRepositoryImpl
import ru.cometrica.demoapp.device.LocationManagerImpl
import ru.cometrica.demoapp.domain.LocationManager
import ru.cometrica.demoapp.domain.author.GetCurrentAuthor
import ru.cometrica.demoapp.domain.document.StreamDocumentList
import ru.cometrica.demoapp.domain.document.SyncDocumentList
import ru.cometrica.demoapp.domain.github.FindGitHubRepo
import ru.cometrica.demoapp.domain.location.StreamCurrentLocation
import ru.cometrica.demoapp.presentation.document.presenter.DocumentListPresenter
import ru.cometrica.demoapp.util.SchedulerProvider
import ru.cometrica.demoapp.util.SchedulerProviderImpl

val appModule = module {

    //region Presenters

    factory { DocumentListPresenter(get(), get(), get(), get(), get()) }

    //endregion

    //region Interactors

    single { StreamDocumentList(get()) }
    single { SyncDocumentList(get()) }
    single { StreamCurrentLocation(get()) }
    single { GetCurrentAuthor() }
    single { FindGitHubRepo(get()) }

    //endregion

    //region Repositories

    single<DocumentsRepository>(createdAtStart = true) { DocumentsRepositoryImpl() }
    single<LocationManager>(createdAtStart = true) { LocationManagerImpl() }

    single<SchedulerProvider> { SchedulerProviderImpl() }

    //endregion

}
