package com.example.premierleagueapp.model.apiResponses

import com.example.premierleagueapp.model.Match
import kotlinx.serialization.Serializable

@Serializable
data class MatchApiResponse(
    val matches: List<Match>,
)
