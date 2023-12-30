package com.example.premierleagueapp.data

import android.util.Log
import com.example.premierleagueapp.data.database.TeamDao
import com.example.premierleagueapp.data.database.asDbTeam
import com.example.premierleagueapp.data.database.asDomainTeams
import com.example.premierleagueapp.model.MatchApiResponse
import com.example.premierleagueapp.network.Match
import com.example.premierleagueapp.network.SoccerApiService
import com.example.premierleagueapp.network.Team
import com.example.premierleagueapp.network.asDomainObjects
import com.example.premierleagueapp.network.getTeamsAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import retrofit2.Response

interface SoccerRepository {
    // suspend fun getTeams(apiKey: String): List<Team>?
    // suspend fun getSingleTeam(teamId: Int, apiKey: String): Team?
    suspend fun getMatchesByTeam(teamId: Int, apiKey: String): List<Match>?

    fun getTeams(): Flow<List<Team>>

    // fun getSingleTeam(id: Int): Flow<Team>
    suspend fun getSingleTeam(id: Int): Team?

    suspend fun insert(item: Team)

    suspend fun refresh()
}

class CachingSoccerRepository(
    private val teamDao: TeamDao,
    private val soccerApiService: SoccerApiService,
) : SoccerRepository {
    override suspend fun insert(item: Team) {
        teamDao.insertTeam(item.asDbTeam())
    }
    override suspend fun getMatchesByTeam(teamId: Int, apiKey: String): List<Match>? {
        val result: Response<MatchApiResponse> =
            soccerApiService.getMatcesByTeam(teamId, "e2b1a771617b483bb629ab23272611a3")
        val matches: List<Match>? = result.body()?.matches
        return matches
    }

    override fun getTeams(): Flow<List<Team>> {
        return teamDao.getAllItems().map {
            it.asDomainTeams()
        }.onEach {
            if (it.isEmpty()) {
                refresh()
            }
        }
    }

    /*
    override fun getSingleTeam(id: Int): Flow<Team> {
        Log.i("Debug", "Inside getSingleTeam in Repository")
        return teamDao.getItem(id).map {
            Log.i("Debug", "Inside map: ${it.asDomainTeam()}")
            it.asDomainTeam()
        }
    }
*/
    override suspend fun getSingleTeam(id: Int): Team? {
        val result: Response<Team> = soccerApiService.getSingleTeam(id, "e2b1a771617b483bb629ab23272611a3")
        val team: Team? = result?.body()
        return team
    }

    override suspend fun refresh() {
        soccerApiService.getTeamsAsFlow().collect {
            val teams: List<Team>? = it?.body()?.teams
            teams?.asDomainObjects()
            for (team in teams!!) {
                Log.i("TESTTTT", "refresh: $team")
                insert(team)
            }
        }

        /*soccerApiService.getTeamAsFlow(id).collect {
            val team: Team? = it?.body()
            Log.i("TESTTTT", "refresh: $team")
        }*/
    }
}
/*class ApiSoccerRepository(
    private val soccerApiService: SoccerApiService,
) : SoccerRepository {
    override suspend fun getTeams(apiKey: String): List<Team>? {
        val result: Response<TeamApiResponse> = soccerApiService.getTeams("e2b1a771617b483bb629ab23272611a3")
        val teams: List<Team>? = result?.body()?.teams
        return teams?.asDomainObjects()
    }

    override suspend fun getSingleTeam(teamId: Int, apiKey: String): Team? {
        val result: Response<Team> = soccerApiService.getSingleTeam(teamId, "e2b1a771617b483bb629ab23272611a3")
        val team: Team? = result?.body()
        return team
    }

    override suspend fun getMatchesByTeam(teamId: Int, apiKey: String): List<Match>? {
        val result: Response<MatchApiResponse> =
            soccerApiService.getMatcesByTeam(teamId, "e2b1a771617b483bb629ab23272611a3")
        val matches: List<Match>? = result.body()?.matches
        return matches
    }
}*/
