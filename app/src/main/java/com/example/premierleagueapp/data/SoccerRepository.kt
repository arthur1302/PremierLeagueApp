package com.example.premierleagueapp.data

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

    /**
     * Function that inserts teams in the database
     *
     * @param team an instance of [Team]
     */
    override suspend fun insert(team: Team) {
        teamDao.insert(team.asDbTeam())
    }

    /**
     * Function that inserts matches in the database
     *
     * @param match an instance of [Match]
     */
    override suspend fun insert(match: Match) {
        matchDao.insert(match.asDbMatch())
    }

    /**
     * Function that inserts tables in the database
     *
     * @param table an instance of [Table]
     */
    override suspend fun insert(table: Table) {
        tableDao.insert(table.asDbTable())
    }

    /**
     * Function that drops all matches in the database
     */
    override suspend fun dropMatches() {
        matchDao.drop()
    }

    /**
     * Function that returns a flow of all teams
     *
     * @return a [Flow] of a [List] of [Team] objects
     */
    override fun getTeams(): Flow<List<Team>> {
        return teamDao.getAllTeams().map {
            it.asDomainTeams()
        }.onEach {
            if (it.isEmpty()) {
                refresh()
            }
        }
    }

    /**
     * Function that returns a flow of all rankings
     *
     * @return a [Flow] of a [List] of [Table] objects
     */
    override fun getStandings(): Flow<List<Table>> {
        return tableDao.getAllStandings().map {
            it.asDomainTables()
        }.onEach {
            if (it.isEmpty()) {
                refreshStandings()
            }
        }
    }

    /**
     * Function that returns a flow of a team
     *
     * @param teamId [Int]
     * @return a [Flow] of a [Team] instance
     */
    override fun getSingleTeam(teamId: Int): Flow<Team?> {
        return teamDao.getTeam(teamId).map {
            it.asDomainTeam()
        }
    }

    /**
     * Function that refreshes the database
     */
    override suspend fun refresh() {
        soccerApiService.getTeamsAsFlow().collect {
            val teams: List<Team> = it.body()!!.teams
            for (team in teams) {
                insert(team)
            }
        }
    }

    /**
     * Function that refreshes the database
     */
    override suspend fun refreshStandings() {
        soccerApiService.getTablesAsFlow().collect {
            val standings: List<Standings> = it.body()!!.standings
            for (standing in standings) {
                if (standing.stage == "REGULAR_SEASON") {
                    for (table in standing.table) {
                        insert(table)
                    }
                }
            }
        }
    }

    /**
     * Function that returns a flow of a list of matches
     *
     * @param teamId [Int]
     * @return a [Flow] of a [List] of [Match] instances
     */
    override suspend fun getMatchesByTeam(teamId: Int): Flow<List<Match>> = withContext(Dispatchers.IO) {
        dropMatches()
        return@withContext matchDao.getAllMatches().map { it.asDomainMatches() }.onEach { it ->
            if (it.isEmpty()) {
                soccerApiService.getMatchesAsFlow(teamId).collect {
                    val matches: List<Match> = it.body()!!.matches
                    for (match in matches) {
                        insert(match)
                    }
                }
            }
        }
    }
}
