package ru.cometrica.demoapp.domain.model

data class Repository(
    val name: String,
    val description: String?,
    val forkCount: Int,
    val url: String
)
