package com.example.premierleagueapp.fake

import com.example.premierleagueapp.data.SoccerRepository
import com.example.premierleagueapp.model.Match
import com.example.premierleagueapp.model.Table
import com.example.premierleagueapp.model.Team
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Fake API soccerRepository to test
 *
 * @author Arthur Haus
 */
class FakeApiSoccerRepository : SoccerRepository {

    private val teamsList = mutableListOf<Team>()
    private val matchesList = mutableListOf<Match>()
    private val tablesList = mutableListOf<Table>()

    override suspend fun insert(team: Team) {
        teamsList.add(team)
    }

    override suspend fun insert(match: Match) {
        matchesList.add(match)
    }

    override suspend fun insert(table: Table) {
        tablesList.add(table)
    }

    override fun getTeams(): Flow<List<Team>> {
        return flow { emit(teamsList) }
    }

    override fun getStandings(): Flow<List<Table>> {
        return flow { emit(tablesList) }
    }

    override fun getSingleTeam(teamId: Int): Flow<Team?> {
        return flow { emit(teamsList.find { it.id == teamId }) }
    }

    override suspend fun getMatchesByTeam(teamId: Int): Flow<List<Match>> {
        return flow { emit(matchesList.filter { it.homeTeam.id == teamId || it.awayTeam.id == teamId }) }
    }

    override suspend fun refresh() {
    }

    override suspend fun refreshStandings() {
    }

    override suspend fun dropMatches() {
        matchesList.clear()
    }
}
