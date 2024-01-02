package com.example.premierleagueapp.network

import com.example.premierleagueapp.model.MatchApiResponse
import com.example.premierleagueapp.model.TeamApiResponse
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface SoccerApiService {
    @GET("competitions/PL/teams")
    suspend fun getTeams(@Header("X-Auth-Token") apiKey: String): Response<TeamApiResponse>

    @GET("teams/{id}")
    suspend fun getSingleTeam(@Path("id") teamId: Int, @Header("X-Auth-Token") apiKey: String): Response<Team>

    @GET("teams/{id}/matches")
    suspend fun getMatchesByTeam(@Path("id") teamId: Int, @Header("X-Auth-Token") apiKey: String): Response<MatchApiResponse>
}

fun SoccerApiService.getTeamsAsFlow() = flow { emit(getTeams("e2b1a771617b483bb629ab23272611a3")) }

fun SoccerApiService.getMatchesAsFlow(id: Int) = flow { emit(getMatchesByTeam(id, "e2b1a771617b483bb629ab23272611a3")) }
