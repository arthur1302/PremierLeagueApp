package com.example.premierleagueapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Table(
    val position: Int,
    val team: Team,
    val points: Int,
)
