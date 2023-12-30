package com.example.premierleagueapp.data

import com.example.premierleagueapp.network.Match
import kotlinx.serialization.Serializable

// Een data class om de volledige JSON-respons te vertegenwoordigen
@Serializable
data class MatchApiResponse(
    val matches: List<Match>,
)
