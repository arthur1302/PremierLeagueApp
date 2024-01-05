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
