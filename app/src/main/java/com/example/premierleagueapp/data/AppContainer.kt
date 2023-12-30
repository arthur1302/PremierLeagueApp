package com.example.premierleagueapp.data

import com.example.premierleagueapp.network.SoccerApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val soccerRepository: SoccerRepository
}

class DefaultAppContainer() : AppContainer {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.football-data.org/v4/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val teamService: SoccerApiService by lazy {
        retrofit.create(SoccerApiService::class.java)
    }

    override val soccerRepository: SoccerRepository by lazy {
        ApiSoccerRepository(teamService)
    }
}
