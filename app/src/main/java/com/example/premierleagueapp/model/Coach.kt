package com.example.premierleagueapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Coach(
    val name: String,
    val nationality: String,
)
