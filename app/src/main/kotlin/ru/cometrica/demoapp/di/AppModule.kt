package ru.cometrica.demoapp.di

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.response.CustomTypeAdapter
import com.apollographql.apollo.response.CustomTypeValue
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import ru.cometrica.demoapp.BuildConfig
import ru.cometrica.demoapp.data.repository.DocumentsRepository
import ru.cometrica.demoapp.data.repository.DocumentsRepositoryImpl
import ru.cometrica.demoapp.device.LocationManagerImpl
import ru.cometrica.demoapp.domain.LocationManager
import ru.cometrica.demoapp.domain.author.GetCurrentAuthor
import ru.cometrica.demoapp.domain.document.StreamDocumentList
import ru.cometrica.demoapp.domain.document.SyncDocumentList
import ru.cometrica.demoapp.domain.location.StreamCurrentLocation
import ru.cometrica.demoapp.graphql.type.CustomType
import ru.cometrica.demoapp.presentation.document.presenter.DocumentListPresenter
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*

// RFC 3339, ex: '2019-02-22T14:53:59Z'
val graphqlDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale("ru", "RU"))

val appModule = module {

    // Presenters
    factory { DocumentListPresenter(get(), get(), get(), get(), get()) }

    // Interactors
    single { StreamDocumentList(get()) }
    single { SyncDocumentList(get()) }
    single { StreamCurrentLocation(get()) }
    single { GetCurrentAuthor() }

    // Repositories
    single<DocumentsRepository>(createdAtStart = true) { DocumentsRepositoryImpl() }
    single<LocationManager>(createdAtStart = true) { LocationManagerImpl(androidApplication()) }

    // Clients
    single<OkHttpClient> {
        OkHttpClient
            .Builder()
            .addInterceptor({ chain ->
                val original = chain.request()
                val builder = original.newBuilder().method(
                    original.method(),
                    original.body()
                )
                builder.addHeader("Authorization", "Bearer " + BuildConfig.AUTH_TOKEN)
                chain.proceed(builder.build())
            })
            .build()
    }

    single<CustomTypeAdapter<URI>> {
        object : CustomTypeAdapter<URI> {
            override fun encode(value: URI): CustomTypeValue<*> =
                CustomTypeValue.GraphQLString(value.toString())

            override fun decode(value: CustomTypeValue<*>): URI =
                URI.create(value.value.toString())
        }
    }

    single<ApolloClient> {
        ApolloClient.builder()
            .serverUrl(BuildConfig.BASE_URL)
            .okHttpClient(get())
            .addCustomTypeAdapter(CustomType.URI, get<CustomTypeAdapter<URI>>())
            .build()
    }

}
