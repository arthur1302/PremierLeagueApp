package com.example.premierleagueapp.network // ktlint-disable filename

import com.example.premierleagueapp.model.Coach
import com.example.premierleagueapp.model.Player
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
    val squad: List<Player>,
)

fun List<Team>.asDomainObjects() =
    map { Team(it.id, it.name, it.shortName, it.crest, it.website, it.tla, it.coach, it.venue, it.squad) }
