package com.example.premierleagueapp.model

import kotlinx.serialization.Serializable

/**
 * Represents the data of a Standings model
 *
 * @author Arthur Haus
 */
@Serializable
data class Standings(
    val stage: String,
    val table: List<Table>,
)
