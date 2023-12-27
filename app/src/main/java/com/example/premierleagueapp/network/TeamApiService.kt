package com.example.premierleagueapp.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

interface TeamApiService {
    @GET("/competitions/PL/teams")
    suspend fun getTeams(): List<ApiTeam>
}

private var retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(
        Json.asConverterFactory("application/json".toMediaType()),
    )
    .baseUrl("http://api.football-data.org/v4")
    .build()

object TeamApi {
    val teamService: TeamApiService by lazy {
        retrofit.create(TeamApiService::class.java)
    }
}
