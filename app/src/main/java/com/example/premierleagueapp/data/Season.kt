package com.example.premierleagueapp.data

import kotlinx.serialization.Serializable

@Serializable
data class Season(
    val id: Int,
    val startDate: String,
    val endDate: String,
    val currentMatchday: Int,
    val winner: String?,
)
