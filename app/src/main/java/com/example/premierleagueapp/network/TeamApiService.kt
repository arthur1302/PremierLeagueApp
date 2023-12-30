package com.example.premierleagueapp.network

import com.example.premierleagueapp.data.MatchApiResponse
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

    @GET("teams/{id}/matches")
    suspend fun getMatcesByTeam(@Path("id") teamId: Int, @Header("X-Auth-Token") apiKey: String): Response<MatchApiResponse>
}

@Serializable
data class Filters(val season: String)

