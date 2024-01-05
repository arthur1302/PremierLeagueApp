package com.example.premierleagueapp.model.apiResponses

import com.example.premierleagueapp.model.Match
import kotlinx.serialization.Serializable

/**
 * Represents the data of a match API response
 *
 * @author Arthur Haus
 */
@Serializable
data class MatchApiResponse(
    val matches: List<Match>,
)
