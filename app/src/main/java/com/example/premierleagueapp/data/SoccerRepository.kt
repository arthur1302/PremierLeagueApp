package com.example.premierleagueapp.data

import android.util.Log
import com.example.premierleagueapp.data.database.TeamDao
import com.example.premierleagueapp.data.database.asDbTeam
import com.example.premierleagueapp.data.database.asDomainTeam
import com.example.premierleagueapp.data.database.asDomainTeams
import com.example.premierleagueapp.model.MatchApiResponse
import com.example.premierleagueapp.network.Match
import com.example.premierleagueapp.network.SoccerApiService
import com.example.premierleagueapp.network.Team
import com.example.premierleagueapp.network.getTeamsAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import retrofit2.Response

interface SoccerRepository {
    // suspend fun getTeams(apiKey: String): List<Team>?
    // suspend fun getSingleTeam(teamId: Int, apiKey: String): Team?
    // suspend fun getMatchesByTeam(teamId: Int, apiKey: String): List<Match>?

    suspend fun insert(team: Team)

    fun getTeams(): Flow<List<Team>>

    fun getSingleTeam(teamId: Int): Flow<Team?>

    suspend fun getMatchesByTeam(teamId: Int, apiKey: String): List<Match>?

    suspend fun refresh()
}

class CachingTeamRespository(
    private val teamDao: TeamDao,
    private val teamApiService: SoccerApiService,
) : SoccerRepository {

    override suspend fun insert(team: Team) {
        teamDao.insert(team.asDbTeam())
    }

    override fun getTeams(): Flow<List<Team>> {
        return teamDao.getAllTeams().map {
            it.asDomainTeams()
        }.onEach {
            if (it.isEmpty()) {
                refresh()
            }
        }
    }

    override fun getSingleTeam(teamId: Int): Flow<Team?> {
        return teamDao.getTeam(teamId).map {
            it.asDomainTeam()
        }
    }

    override suspend fun refresh() {
        teamApiService.getTeamsAsFlow().collect() {
            val teams: List<Team> = it.body()!!.teams
            for (team in teams) {
                Log.i("Test", "refresh: $team")
                insert(team)
            }
        }
    }

    override suspend fun getMatchesByTeam(teamId: Int, apiKey: String): List<Match>? {
        val result: Response<MatchApiResponse> =
            teamApiService.getMatcesByTeam(teamId, "e2b1a771617b483bb629ab23272611a3")
        val matches: List<Match>? = result.body()?.matches
        return matches
    }
}

/*private fun Coach.asDbCoach(): DbCoach {
    return DbCoach(name, nationality)
}*/

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
}
*/
