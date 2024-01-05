package com.example.premierleagueapp.network

import com.example.premierleagueapp.model.Team
import com.example.premierleagueapp.model.apiResponses.MatchApiResponse
import com.example.premierleagueapp.model.apiResponses.TableApiResponse
import com.example.premierleagueapp.model.apiResponses.TeamApiResponse
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

    @GET("competitions/PL/standings")
    suspend fun getTables(@Header("X-Auth-Token") apiKey: String): Response<TableApiResponse>
}

const val apiKey = ApiConfig.API_TOKEN
fun SoccerApiService.getTeamsAsFlow() = flow { emit(getTeams(apiKey)) }

fun SoccerApiService.getTablesAsFlow() = flow { emit(getTables(apiKey)) }

fun SoccerApiService.getMatchesAsFlow(id: Int) = flow { emit(getMatchesByTeam(id, apiKey)) }
