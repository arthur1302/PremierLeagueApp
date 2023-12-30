package com.example.premierleagueapp.model

import com.example.premierleagueapp.network.Team
import kotlinx.serialization.Serializable

// Een data class om de volledige JSON-respons te vertegenwoordigen
@Serializable
data class TeamApiResponse(
    val count: Int,
    val competition: Competition,
    val season: Season,
    val teams: List<Team>,
)
