package com.example.premierleagueapp.model

import kotlinx.serialization.Serializable

@Serializable
data class MatchApiResponse(
    val matches: List<Match>,
)
