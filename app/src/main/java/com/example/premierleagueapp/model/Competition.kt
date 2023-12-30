package com.example.premierleagueapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Competition(
    val id: Int,
    val name: String,
    val code: String,
    val type: String,
    val emblem: String,
)
