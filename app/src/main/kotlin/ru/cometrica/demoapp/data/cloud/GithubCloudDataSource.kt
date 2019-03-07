package ru.cometrica.demoapp.data.cloud

import io.reactivex.Maybe
import ru.cometrica.demoapp.domain.model.Repository

interface GithubCloudDataSource {

    fun findRepo(name: String, owner: String): Maybe<Repository>

}
