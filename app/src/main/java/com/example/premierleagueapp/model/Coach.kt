package com.example.premierleagueapp.model

import kotlinx.serialization.Serializable

/**
 * Represents the data of a Coach model
 *
 * @author Arthur Haus
 */
@Serializable
data class Coach(
    val name: String,
    val nationality: String,
)
