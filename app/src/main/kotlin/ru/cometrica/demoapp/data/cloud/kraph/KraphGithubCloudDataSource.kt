package ru.cometrica.demoapp.data.cloud.kraph

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import ru.cometrica.demoapp.BuildConfig
import ru.cometrica.demoapp.data.cloud.GithubCloudDataSource
import ru.cometrica.demoapp.domain.model.Repository

class KraphGithubCloudDataSource(val client: OkHttpClient) : GithubCloudDataSource {

    companion object {
        val JSON: MediaType = MediaType.get("application/json; charset=utf-8")
    }

    override fun findRepo(name: String, owner: String) =
        Single
            .create<String> { emitter ->
                val body = RequestBody.create(JSON, findGitHubRepo(name, owner).toRequestString())
                val request = Request.Builder().url(BuildConfig.BASE_URL).post(body).build()
                val result = client.newCall(request).execute()
                if (result.isSuccessful) {
                    result.body()?.string()
                        ?.let { emitter.onSuccess(it) }
                        ?: emitter.onError(Exception("Empty body"))
                } else {
                    emitter.onError(Exception(result.networkResponse().toString()))
                }
            }
            .map { parseResponse(it).data.repository }
            .toMaybe()
            .subscribeOn(Schedulers.io())

    private fun parseResponse(json: String) =
        Json.parse(GithubResponse.serializer(RepositoryResponse.serializer()), json)

}

@Serializable
data class GithubResponse<T>(
    val data: T
)

@Serializable
data class RepositoryResponse(
    val repository: Repository
)
