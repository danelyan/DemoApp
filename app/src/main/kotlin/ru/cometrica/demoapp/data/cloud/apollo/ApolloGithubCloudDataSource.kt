package ru.cometrica.demoapp.data.cloud.apollo

import com.apollographql.apollo.ApolloClient
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers
import ru.cometrica.demoapp.data.cloud.GithubCloudDataSource
import ru.cometrica.demoapp.domain.model.Repository

class ApolloGithubCloudDataSource(private val apollo: ApolloClient) : GithubCloudDataSource {

    override fun findRepo(name: String, owner: String) =
        apollo.query(FindQuery.builder().name(name).owner(owner).build())
            .toSingle()
            .flatMapMaybe { response ->
                response.data()?.repository()
                    ?.let { Maybe.just(it) }
                    ?: Maybe.empty()
            }
            .map {
                Repository(
                    name = it.name,
                    description = it.description,
                    forkCount = it.forkCount,
                    url = it.url.toASCIIString()
                )
            }
            .subscribeOn(Schedulers.io())

}
