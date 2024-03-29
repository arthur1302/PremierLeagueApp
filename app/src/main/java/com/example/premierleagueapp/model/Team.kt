package com.example.premierleagueapp.model

import kotlinx.serialization.Serializable

/**
 * Represents the data of a Team model
 *
 * @author Arthur Haus
 */
@Serializable
data class Team(
    val id: Int,
    val name: String,
    val shortName: String,
    val crest: String,
    val website: String,
    val tla: String,
    val coach: Coach,
    val venue: String,
    val squad: List<Player>,
)
