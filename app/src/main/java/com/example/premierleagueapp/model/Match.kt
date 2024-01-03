package com.example.premierleagueapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Match(
    val id: Int,
    val competition: Competition,
    val status: String,
    val utcDate: String,
    val homeTeam: Team,
    val awayTeam: Team,
)

fun List<Match>.asDomainObjects() =
    map { Match(it.id, it.competition, it.status, it.utcDate, it.homeTeam, it.awayTeam) }
