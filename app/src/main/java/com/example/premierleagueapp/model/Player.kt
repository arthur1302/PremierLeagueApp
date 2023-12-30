package com.example.premierleagueapp.model

import kotlinx.serialization.Serializable
@Serializable
data class Player(
    val name: String,
    val position: String,
    val nationality: String,
)
