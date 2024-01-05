package com.example.premierleagueapp.model.apiResponses

import com.example.premierleagueapp.model.Standings
import kotlinx.serialization.Serializable

@Serializable
data class TableApiResponse(
    val standings: List<Standings>,
)
