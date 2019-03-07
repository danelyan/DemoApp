package ru.cometrica.demoapp.data.cloud.kraph

import me.lazmaid.kraph.Kraph

fun findGitHubRepo(name: String, owner: String) =
    Kraph {
        query {
            fieldObject(
                name = "repository",
                args = mapOf("name" to name, "owner" to owner)
            ) {
                field("name")
                field("description")
                field("forkCount")
                field("url")
            }
        }
    }
