package com.example.premierleagueapp.network

import kotlinx.serialization.Serializable

@Serializable
data class ApiTeam(
    val name: String,
    val city: String,
)
