package com.example.premierleagueapp.network

import com.example.premierleagueapp.data.TeamApiResponse
import kotlinx.serialization.Serializable
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface TeamApiService {
    @GET("competitions/PL/teams")
    suspend fun getTeams(@Header("X-Auth-Token") apiKey: String): Response<TeamApiResponse>

    @GET("teams/{id}")
    suspend fun getSingleTeam(@Path("id") teamId: Int, @Header("X-Auth-Token") apiKey: String): Response<Team>
}

@Serializable
data class Filters(val season: String)

var retrofit = Retrofit.Builder()
    .baseUrl("https://api.football-data.org/v4/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object TeamApi {
    val teamService: TeamApiService by lazy {
        retrofit.create(TeamApiService::class.java)
    }
}
