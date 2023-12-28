package com.example.premierleagueapp.network // ktlint-disable filename

import kotlinx.serialization.Serializable

@Serializable
data class Team(
    val id: Int,
    val name: String,
    val shortName: String,
    val crest: String,
    val website: String,
)

fun List<Team>.asDomainObjects() =
    map { Team(it.id, it.name, it.shortName, it.crest, it.website) }
