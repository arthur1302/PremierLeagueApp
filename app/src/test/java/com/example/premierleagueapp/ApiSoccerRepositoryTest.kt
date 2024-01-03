package com.example.premierleagueapp

import com.example.premierleagueapp.fake.FakeApiSoccerRepository
import com.example.premierleagueapp.fake.FakeDataSource
import com.example.premierleagueapp.model.Match
import com.example.premierleagueapp.model.MatchApiResponse
import com.example.premierleagueapp.model.Team
import com.example.premierleagueapp.model.TeamApiResponse
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.Response

class ApiSoccerRepositoryTest {

    @Test
    fun apiSoccerRepository_getTeams_verifyTeams() = runTest {
        val apiSoccerRepository = FakeApiSoccerRepository()

        val teamsList = mutableListOf<Team>()

        val result: Response<TeamApiResponse> = FakeDataSource.getFakeTeamsResponse()
        val fakeTeams: List<Team>? = result?.body()?.teams

        for (team in fakeTeams!!) {
            apiSoccerRepository.insert(team)
        }

        apiSoccerRepository.getTeams().collect { teams ->

            teamsList.addAll(teams)
            println("Collected teams: $teamsList")
        }

        assertEquals(fakeTeams, teamsList)
    }

    @Test
    fun apiSoccerRepository_getTeam_verifyTeam() = runTest {
        val apiSoccerRepository = FakeApiSoccerRepository()

        val resultFakeTeams: Response<TeamApiResponse> = FakeDataSource.getFakeTeamsResponse()
        val fakeTeams: List<Team>? = resultFakeTeams?.body()?.teams

        for (team in fakeTeams!!) {
            apiSoccerRepository.insert(team)
        }

        var team: Team? = null
        apiSoccerRepository.getSingleTeam(1).collect {
                result ->
            team = result!!
        }

        val result: Response<Team> = FakeDataSource.getFakeTeamResponse()
        val fakeTeam: Team? = result?.body()

        assertEquals(fakeTeam, team)
    }

    @Test
    fun apiSoccerRepository_getMatches_verifyMatches() = runTest {
        val apiSoccerRepository = FakeApiSoccerRepository()

        val matchList = mutableListOf<Match>()

        val result: Response<MatchApiResponse> = FakeDataSource.getFakeMatchesByTeamResponse()
        val fakeMatches: List<Match>? = result?.body()?.matches

        for (match in fakeMatches!!) {
            apiSoccerRepository.insert(match)
        }

        apiSoccerRepository.getMatchesByTeam(1).collect { match ->
            matchList.addAll(match)
        }

        assertEquals(fakeMatches, matchList)
    }
}
