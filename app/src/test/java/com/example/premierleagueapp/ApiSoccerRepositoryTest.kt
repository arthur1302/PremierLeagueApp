package com.example.premierleagueapp

import com.example.premierleagueapp.data.ApiSoccerRepository
import com.example.premierleagueapp.fake.FakeDataSource
import com.example.premierleagueapp.fake.FakeSoccerApiService
import com.example.premierleagueapp.model.MatchApiResponse
import com.example.premierleagueapp.model.TeamApiResponse
import com.example.premierleagueapp.network.Match
import com.example.premierleagueapp.network.Team
import com.example.premierleagueapp.network.asDomainObjects
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.Response

class ApiSoccerRepositoryTest {
    @Test
    fun apiSoccerRepository_getTeams_verifyTeams() = runTest {
        val repository = ApiSoccerRepository(FakeSoccerApiService())

        val result: Response<TeamApiResponse> = FakeDataSource.getFakeTeamsResponse()
        val teams: List<Team>? = result?.body()?.teams

        assertEquals(teams?.asDomainObjects(), repository.getTeams(""))
    }

    @Test
    fun apiSoccerRepository_getTeam_verifyTeam() = runTest {
        val repository = ApiSoccerRepository(FakeSoccerApiService())

        val result: Response<Team> = FakeDataSource.getFakeTeamResponse()
        val team: Team? = result?.body()

        assertEquals(team, repository.getSingleTeam(0, ""))
    }

    @Test
    fun apiSoccerRepository_getMatches_verifyMatches() = runTest {
        val repository = ApiSoccerRepository(FakeSoccerApiService())

        val result: Response<MatchApiResponse> = FakeDataSource.getFakeMatchesByTeamResponse()
        val matches: List<Match>? = result?.body()?.matches

        assertEquals(matches?.asDomainObjects(), repository.getMatchesByTeam(0, ""))
    }
}
