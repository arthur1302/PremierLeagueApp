package com.example.premierleagueapp.fake

import com.example.premierleagueapp.data.SoccerRepository
import com.example.premierleagueapp.model.MatchApiResponse
import com.example.premierleagueapp.model.TeamApiResponse
import com.example.premierleagueapp.network.Match
import com.example.premierleagueapp.network.Team
import com.example.premierleagueapp.network.asDomainObjects
import retrofit2.Response

class FakeApiSoccerRepository : SoccerRepository {
    override suspend fun getTeams(apiKey: String): List<Team>? {
        val result: Response<TeamApiResponse> = FakeDataSource.getFakeTeamsResponse()
        val teams: List<Team>? = result?.body()?.teams
        return teams?.asDomainObjects()
    }

    override suspend fun getSingleTeam(teamId: Int, apiKey: String): Team? {
        return FakeDataSource.team1
    }

    override suspend fun getMatchesByTeam(teamId: Int, apiKey: String): List<Match>? {
        val result: Response<MatchApiResponse> = FakeDataSource.getFakeMatchesByTeamResponse()
        val matches: List<Match>? = result?.body()?.matches
        return matches?.asDomainObjects()
    }
}
