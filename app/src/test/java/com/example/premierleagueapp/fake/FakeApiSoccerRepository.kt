package com.example.premierleagueapp.fake

import com.example.premierleagueapp.data.SoccerRepository
import com.example.premierleagueapp.network.Match
import com.example.premierleagueapp.network.Team
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeApiSoccerRepository : SoccerRepository {

    private val teamsList = mutableListOf<Team>()
    private val matchesList = mutableListOf<Match>()

    override suspend fun insert(team: Team) {
        teamsList.add(team)
    }

    override suspend fun insert(match: Match) {
        matchesList.add(match)
    }

    override fun getTeams(): Flow<List<Team>> {
        return flow { emit(teamsList) }
    }

    override fun getSingleTeam(teamId: Int): Flow<Team?> {
        return flow { emit(teamsList.find { it.id == teamId }) }
    }

    override suspend fun getMatchesByTeam(teamId: Int): Flow<List<Match>> {
        return flow { emit(matchesList.filter { it.homeTeam.id == teamId || it.awayTeam.id == teamId }) }
    }

    override suspend fun refresh() {
    }

    override suspend fun dropMatches() {
        matchesList.clear()
    }
}
