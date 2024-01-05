package com.example.premierleagueapp.model

import kotlinx.serialization.Serializable

/**
 * Represents the data of a Season model
 *
 * @author Arthur Haus
 */
@Serializable
data class Season(
    val id: Int,
    val startDate: String,
    val endDate: String,
    val currentMatchDay: Int,
    val winner: String?,
)
