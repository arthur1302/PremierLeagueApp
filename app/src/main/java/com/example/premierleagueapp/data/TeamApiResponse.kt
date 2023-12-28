package com.example.premierleagueapp.data

import com.example.premierleagueapp.network.Filters
import com.example.premierleagueapp.network.Team
import kotlinx.serialization.Serializable

// Een data class om de volledige JSON-respons te vertegenwoordigen
@Serializable
data class TeamApiResponse(
    val count: Int,
    val filters: Filters,
    val competition: Competition,
    val season: Season,
    val teams: List<Team>,
)
