package com.example.premierleagueapp.network

import com.example.premierleagueapp.data.Competition
import kotlinx.serialization.Serializable

@Serializable
data class Match(
    val competition: Competition,
    val status: String,
    val utcDate: String,
    val homeTeam: Team,
    val awayTeam: Team,
)

fun List<Match>.asDomainObjects() =
    map { Match(it.competition, it.status, it.utcDate, it.homeTeam, it.awayTeam) }
