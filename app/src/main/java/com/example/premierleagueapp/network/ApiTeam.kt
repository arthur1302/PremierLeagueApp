package com.example.premierleagueapp.network // ktlint-disable filename

import com.example.premierleagueapp.data.Coach
import kotlinx.serialization.Serializable

@Serializable
data class Team(
    val id: Int,
    val name: String,
    val shortName: String,
    val crest: String,
    val website: String,
    val tla: String,
    val coach: Coach,
    val venue: String,
)

fun List<Team>.asDomainObjects() =
    map { Team(it.id, it.name, it.shortName, it.crest, it.website, it.tla, it.coach, it.venue) }
