package ru.cometrica.demoapp.domain.github

import io.reactivex.Maybe
import ru.cometrica.demoapp.data.cloud.GithubCloudDataSource
import ru.cometrica.demoapp.domain.InteractorParametrized
import ru.cometrica.demoapp.domain.model.Repository

class FindGitHubRepo(private val cloudDataSource: GithubCloudDataSource) :
    InteractorParametrized<FindGitHubRepoParam, Maybe<Repository>> {

    override fun build(param: FindGitHubRepoParam): Maybe<Repository> =
        cloudDataSource.findRepo(param.name, param.owner)
}

data class FindGitHubRepoParam(
    val name: String,
    val owner: String
)
