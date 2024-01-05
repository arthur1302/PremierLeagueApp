package com.example.premierleagueapp.data

import android.util.Log
import com.example.premierleagueapp.data.database.dao.MatchDao
import com.example.premierleagueapp.data.database.dao.TableDao
import com.example.premierleagueapp.data.database.dao.TeamDao
import com.example.premierleagueapp.data.database.entities.asDbMatch
import com.example.premierleagueapp.data.database.entities.asDbTable
import com.example.premierleagueapp.data.database.entities.asDbTeam
import com.example.premierleagueapp.data.database.entities.asDomainMatches
import com.example.premierleagueapp.data.database.entities.asDomainTables
import com.example.premierleagueapp.data.database.entities.asDomainTeam
import com.example.premierleagueapp.data.database.entities.asDomainTeams
import com.example.premierleagueapp.model.Match
import com.example.premierleagueapp.model.Standings
import com.example.premierleagueapp.model.Table
import com.example.premierleagueapp.model.Team
import com.example.premierleagueapp.network.SoccerApiService
import com.example.premierleagueapp.network.getMatchesAsFlow
import com.example.premierleagueapp.network.getTablesAsFlow
import com.example.premierleagueapp.network.getTeamsAsFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

interface SoccerRepository {

    suspend fun insert(team: Team)

    suspend fun insert(match: Match)

    suspend fun insert(table: Table)

    fun getTeams(): Flow<List<Team>>

    fun getStandings(): Flow<List<Table>>

    fun getSingleTeam(teamId: Int): Flow<Team?>

    suspend fun getMatchesByTeam(teamId: Int): Flow<List<Match>>

    suspend fun refresh()
    suspend fun refreshStandings()

    suspend fun dropMatches()
}

class CachingTeamsRepository(
    private val teamDao: TeamDao,
    private val soccerApiService: SoccerApiService,
    private val matchDao: MatchDao,
    private val tableDao: TableDao,
) : SoccerRepository {

    override suspend fun insert(team: Team) {
        teamDao.insert(team.asDbTeam())
    }

    override suspend fun insert(match: Match) {
        matchDao.insert(match.asDbMatch())
    }

    override suspend fun insert(table: Table) {
        tableDao.insert(table.asDbTable())
    }

    override suspend fun dropMatches() {
        matchDao.drop()
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

    override fun getStandings(): Flow<List<Table>> {
        return tableDao.getAllStandings().map {
            it.asDomainTables()
        }.onEach {
            if (it.isEmpty()) {
                refreshStandings()
            }
        }
    }

    override fun getSingleTeam(teamId: Int): Flow<Team?> {
        return teamDao.getTeam(teamId).map {
            it.asDomainTeam()
        }
    }

    override suspend fun refresh() {
        soccerApiService.getTeamsAsFlow().collect {
            val teams: List<Team> = it.body()!!.teams
            for (team in teams) {
                Log.i("Test", "refresh: $team")
                insert(team)
            }
        }
    }

    override suspend fun refreshStandings() {
        soccerApiService.getTablesAsFlow().collect {
            val standings: List<Standings> = it.body()!!.standings
            Log.i("TESSSSTTT", standings.toString())
            for (standing in standings) {
                if (standing.stage == "REGULAR_SEASON") {
                    for (table in standing.table) {
                        Log.i("Test", "refresh: $table")
                        insert(table)
                    }
                }
            }
        }
    }
    override suspend fun getMatchesByTeam(teamId: Int): Flow<List<Match>> = withContext(Dispatchers.IO) {
        dropMatches()
        return@withContext matchDao.getAllMatches().map { it.asDomainMatches() }.onEach { it ->
            if (it.isEmpty()) {
                soccerApiService.getMatchesAsFlow(teamId).collect {
                    val matches: List<Match> = it.body()!!.matches
                    for (match in matches) {
                        Log.i("Test", "refresh: $match")
                        insert(match)
                    }
                }
            }
        }
    }
}
