package com.example.premierleagueapp.data

import kotlinx.serialization.Serializable
@Serializable
data class Player(
    val name: String,
    val position: String,
    val nationality: String,
)
