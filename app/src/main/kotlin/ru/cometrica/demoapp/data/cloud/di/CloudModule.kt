package ru.cometrica.demoapp.data.cloud.di

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.response.CustomTypeAdapter
import com.apollographql.apollo.response.CustomTypeValue
import okhttp3.OkHttpClient
import org.koin.dsl.module
import ru.cometrica.demoapp.BuildConfig
import ru.cometrica.demoapp.data.cloud.GithubCloudDataSource
import ru.cometrica.demoapp.data.cloud.apollo.ApolloGithubCloudDataSource
import ru.cometrica.demoapp.data.cloud.apollo.type.CustomType
import java.net.URI

val cloudModule = module {

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

    single<GithubCloudDataSource> { ApolloGithubCloudDataSource(get()) }

}
