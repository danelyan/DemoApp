package ru.cometrica.demoapp.domain

data class Author(
    val authorId: Long,
    val name: String,
    val surname: String
)

data class Document(
    val id: Long,
    val name: String,
    val path: String,
    val author: Author
)