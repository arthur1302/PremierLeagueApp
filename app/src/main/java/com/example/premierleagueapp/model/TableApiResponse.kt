package com.example.premierleagueapp.model

import kotlinx.serialization.Serializable

@Serializable
data class TableApiResponse(
    val standings: List<Standings>,
)
