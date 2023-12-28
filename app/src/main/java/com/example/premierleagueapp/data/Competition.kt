package com.example.premierleagueapp.data

import kotlinx.serialization.Serializable

@Serializable
data class Competition(
    val id: Int,
    val name: String,
    val code: String,
    val type: String,
    val emblem: String,
)
