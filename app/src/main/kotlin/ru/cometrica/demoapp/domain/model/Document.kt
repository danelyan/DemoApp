package ru.cometrica.demoapp.domain.model

data class Document(
    val id: Long,
    val name: String,
    val path: String,
    val author: Author
)
