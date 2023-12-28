package com.example.premierleagueapp.network

import kotlinx.serialization.Serializable
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface TeamApiService {
    @GET("competitions/PL/teams")
    suspend fun getTeams(@Header("X-Auth-Token") apiKey: String): Response<TeamApiResponse>
}

// Een data class om de volledige JSON-respons te vertegenwoordigen
@Serializable
data class TeamApiResponse(
    val count: Int,
    val filters: Filters,
    val competition: Competition,
    val season: Season,
    val teams: List<Team>,
)

@Serializable
data class Filters(val season: String)

@Serializable
data class Competition(
    val id: Int,
    val name: String,
    val code: String,
    val type: String,
    val emblem: String,
)

@Serializable
data class Season(
    val id: Int,
    val startDate: String,
    val endDate: String,
    val currentMatchday: Int,
    val winner: String?, // Pas dit aan op basis van de werkelijke gegevens
)

@Serializable
data class Team(
    val id: Int,
    val name: String,
    val shortName: String,
    val crest: String,
    val website: String,
)

var retrofit = Retrofit.Builder()
    .baseUrl("https://api.football-data.org/v4/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object TeamApi {
    val teamService: TeamApiService by lazy {
        retrofit.create(TeamApiService::class.java)
    }
}
