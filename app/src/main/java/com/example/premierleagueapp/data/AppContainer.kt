package com.example.premierleagueapp.data

import android.content.Context
import androidx.room.Room
import com.example.premierleagueapp.data.database.SoccerDatabase
import com.example.premierleagueapp.data.database.dao.MatchDao
import com.example.premierleagueapp.data.database.dao.TableDao
import com.example.premierleagueapp.data.database.dao.TeamDao
import com.example.premierleagueapp.network.SoccerApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * App container that creates the API connection
 *
 * @author Arthur Haus
 */
interface AppContainer {
    val soccerRepository: SoccerRepository
}

class DefaultAppContainer(
    applicationContext: Context,
) : AppContainer {

    // Build a retrofit object with the API base url
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.football-data.org/v4/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Retrofit service creation using the SoccerApiService
    private val retrofitService: SoccerApiService by lazy {
        retrofit.create(SoccerApiService::class.java)
    }

    // Lazily initializes an instance of the SoccerDatabase
    private val soccerDb: SoccerDatabase by lazy {
        Room.databaseBuilder(applicationContext, SoccerDatabase::class.java, "Soccer Database")
            .build()
    }

    // Lazily initializes an instance of the TeamDao
    private val teamDao: TeamDao by lazy {
        soccerDb.teamDao()
    }

    // Lazily initializes an instance of the MatchDao
    private val matchDao: MatchDao by lazy {
        soccerDb.matchDao()
    }

    // Lazily initializes an instance of the TableDao
    private val tableDao: TableDao by lazy {
        soccerDb.tableDao()
    }

    // Lazily initializes an instance of the SoccerRepository using CachingTeamsRepository
    // It combines the TeamDao, Retrofit service, MatchDao, and TableDao to create a caching repository
    override val soccerRepository: SoccerRepository by lazy {
        CachingTeamsRepository(teamDao, retrofitService, matchDao, tableDao)
    }
}
