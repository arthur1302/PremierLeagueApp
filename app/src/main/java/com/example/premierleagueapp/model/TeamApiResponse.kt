package com.example.premierleagueapp.model

import kotlinx.serialization.Serializable

@Serializable
data class TeamApiResponse(
    val count: Int,
    val competition: Competition,
    val season: Season,
    val teams: List<Team>,
)
