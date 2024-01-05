package com.example.premierleagueapp.fake

import com.example.premierleagueapp.model.Team
import com.example.premierleagueapp.model.apiResponses.MatchApiResponse
import com.example.premierleagueapp.model.apiResponses.TableApiResponse
import com.example.premierleagueapp.model.apiResponses.TeamApiResponse
import com.example.premierleagueapp.network.SoccerApiService
import retrofit2.Response

class FakeSoccerApiService : SoccerApiService {
    override suspend fun getMatchesByTeam(teamId: Int, apiKey: String): Response<MatchApiResponse> {
        return FakeDataSource.getFakeMatchesByTeamResponse()
    }

    override suspend fun getTables(apiKey: String): Response<TableApiResponse> {
        return FakeDataSource.getFakeTablesResponse()
    }

    override suspend fun getSingleTeam(teamId: Int, apiKey: String): Response<Team> {
        return FakeDataSource.getFakeTeamResponse()
    }

    override suspend fun getTeams(apiKey: String): Response<TeamApiResponse> {
        return FakeDataSource.getFakeTeamsResponse()
    }
}
