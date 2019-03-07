package ru.cometrica.demoapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Repository(
    val name: String,
    val description: String?,
    val forkCount: Int,
    val url: String
)
