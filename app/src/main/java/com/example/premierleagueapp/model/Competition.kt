package com.example.premierleagueapp.model

import kotlinx.serialization.Serializable

/**
 * Represents the data of a Competition model
 *
 * @author Arthur Haus
 */
@Serializable
data class Competition(
    val id: Int,
    val name: String,
    val code: String,
    val type: String,
    val emblem: String,
)
