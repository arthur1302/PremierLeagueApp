package com.example.premierleagueapp.model.apiResponses

import com.example.premierleagueapp.model.Standings
import kotlinx.serialization.Serializable

/**
 * Represents the data of a table API response
 *
 * @author Arthur Haus
 */
@Serializable
data class TableApiResponse(
    val standings: List<Standings>,
)
