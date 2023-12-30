package com.example.premierleagueapp.data

import com.example.premierleagueapp.model.MatchApiResponse
import com.example.premierleagueapp.model.TeamApiResponse
import com.example.premierleagueapp.network.Match
import com.example.premierleagueapp.network.SoccerApiService
import com.example.premierleagueapp.network.Team
import com.example.premierleagueapp.network.asDomainObjects
import retrofit2.Response

interface SoccerRepository {
    suspend fun getTeams(apiKey: String): List<Team>?
    suspend fun getSingleTeam(teamId: Int, apiKey: String): Team?
    suspend fun getMatchesByTeam(teamId: Int, apiKey: String): List<Match>?
}

class ApiSoccerRepository(
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
