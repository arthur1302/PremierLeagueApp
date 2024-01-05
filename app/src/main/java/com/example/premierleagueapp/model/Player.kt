package com.example.premierleagueapp.model

import kotlinx.serialization.Serializable

/**
 * Represents the data of a Player model
 *
 * @author Arthur Haus
 */
@Serializable
data class Player(
    val name: String,
    val position: String,
    val nationality: String,
)
