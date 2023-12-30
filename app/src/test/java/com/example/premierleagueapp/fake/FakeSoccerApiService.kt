package com.example.premierleagueapp.fake

import com.example.premierleagueapp.model.MatchApiResponse
import com.example.premierleagueapp.model.TeamApiResponse
import com.example.premierleagueapp.network.SoccerApiService
import com.example.premierleagueapp.network.Team
import retrofit2.Response

class FakeSoccerApiService : SoccerApiService {
    override suspend fun getMatcesByTeam(teamId: Int, apiKey: String): Response<MatchApiResponse> {
        return FakeDataSource.getFakeMatchesByTeamResponse()
    }

    override suspend fun getSingleTeam(teamId: Int, apiKey: String): Response<Team> {
        return FakeDataSource.getFakeTeamResponse()
    }

    override suspend fun getTeams(apiKey: String): Response<TeamApiResponse> {
        return FakeDataSource.getFakeTeamsResponse()
    }
}
