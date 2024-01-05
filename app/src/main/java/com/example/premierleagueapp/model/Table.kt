package com.example.premierleagueapp.model

import kotlinx.serialization.Serializable

/**
 * Represents the data of a Table model
 *
 * @author Arthur Haus
 */
@Serializable
data class Table(
    val position: Int,
    val team: Team,
    val points: Int,
)
