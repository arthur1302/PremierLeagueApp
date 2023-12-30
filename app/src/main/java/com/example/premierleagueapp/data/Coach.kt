package com.example.premierleagueapp.data

import kotlinx.serialization.Serializable

@Serializable
data class Coach(
    val name: String,
    val nationality: String,
)
