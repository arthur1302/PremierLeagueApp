package com.example.premierleagueapp.data

import android.content.Context
import androidx.room.Room
import com.example.premierleagueapp.data.database.MatchDao
import com.example.premierleagueapp.data.database.SoccerDatabase
import com.example.premierleagueapp.data.database.TeamDao
import com.example.premierleagueapp.network.SoccerApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val soccerRepository: SoccerRepository
}

class DefaultAppContainer(
    applicationContext: Context,
) : AppContainer {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.football-data.org/v4/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitService: SoccerApiService by lazy {
        retrofit.create(SoccerApiService::class.java)
    }

    private val soccerDb: SoccerDatabase by lazy {
        Room.databaseBuilder(applicationContext, SoccerDatabase::class.java, "Soccer Database")
            .build()
    }

    private val teamDao: TeamDao by lazy {
        soccerDb.teamDao()
    }

    private val matchDao: MatchDao by lazy {
        soccerDb.matchDao()
    }

    override val soccerRepository: SoccerRepository by lazy {
        // ApiSoccerRepository(teamService)
        CachingTeamRespository(teamDao, retrofitService, matchDao)
    }
}
