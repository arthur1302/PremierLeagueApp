package com.example.premierleagueapp.model.apiResponses

import com.example.premierleagueapp.model.Competition
import com.example.premierleagueapp.model.Season
import com.example.premierleagueapp.model.Team
import kotlinx.serialization.Serializable

@Serializable
data class TeamApiResponse(
    val count: Int,
    val competition: Competition,
    val season: Season,
    val teams: List<Team>,
)
