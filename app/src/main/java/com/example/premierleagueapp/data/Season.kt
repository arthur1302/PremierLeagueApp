package com.example.premierleagueapp.data

import kotlinx.serialization.Serializable

@Serializable
data class Season(
    val id: Int,
    val startDate: String,
    val endDate: String,
    val currentMatchday: Int,
    val winner: String?, // Pas dit aan op basis van de werkelijke gegevens
)
