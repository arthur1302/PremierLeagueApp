package com.example.premierleagueapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Standings(
    val stage: String,
    val table: List<Table>,
)
