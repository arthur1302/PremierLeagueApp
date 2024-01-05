package com.example.premierleagueapp.fake

import com.example.premierleagueapp.model.Coach
import com.example.premierleagueapp.model.Competition
import com.example.premierleagueapp.model.Match
import com.example.premierleagueapp.model.Player
import com.example.premierleagueapp.model.Season
import com.example.premierleagueapp.model.Standings
import com.example.premierleagueapp.model.Table
import com.example.premierleagueapp.model.Team
import com.example.premierleagueapp.model.apiResponses.MatchApiResponse
import com.example.premierleagueapp.model.apiResponses.TableApiResponse
import com.example.premierleagueapp.model.apiResponses.TeamApiResponse
import retrofit2.Response

object FakeDataSource {

    private val team1 = Team(
        id = 1,
        name = "Dummy Team",
        shortName = "DT",
        crest = "dummy_crest_url",
        website = "https://www.dummyteam.com",
        tla = "DTA",
        coach = Coach("Dummy Coach", "Dummy Nationality"),
        venue = "Dummy Stadium",
        squad = listOf(
            Player("Player 1", "Dummy Nationality", "Nationality"),
            Player("Player 2", "Dummy Nationality", "Nationality"),
            Player("Player 3", "Dummy Nationality", "Nationality"),
        ),

    )

    private val team2 = Team(
        id = 2,
        name = "Dummy Team2",
        shortName = "DT",
        crest = "dummy_crest_url",
        website = "https://www.dummyteam.com",
        tla = "DTA",
        coach = Coach("Dummy Coach", "Dummy Nationality"),
        venue = "Dummy Stadium",
        squad = listOf(
            Player("Player 1", "Dummy Nationality", "Nationality"),
            Player("Player 2", "Dummy Nationality", "Nationality"),
            Player("Player 3", "Dummy Nationality", "Nationality"),
        ),

    )

    private val teamApiResponse = TeamApiResponse(
        count = 2,
        season = Season(
            id = 123,
            startDate = "2023-01-01",
            endDate = "2023-12-31",
            currentMatchDay = 20,
            winner = "Some Team",
        ),
        competition = Competition(
            id = 10,
            name = "Competition",
            code = "CO",
            type = "Group-Stage",
            emblem = "",
        ),
        teams = listOf(team1, team2),
    )

    private val tables1 = Table(
        1,
        team1,
        50,
    )

    private val tables2 = Table(
        2,
        team2,
        45,
    )

    private val standing1 = Standings(
        "REGULAR_SEASON",
        listOf(tables1, tables2),
    )

    private val tableApiResponse = TableApiResponse(
        standings = listOf(standing1),
    )

    fun getFakeTeamsResponse(): Response<TeamApiResponse> {
        return Response.success(teamApiResponse)
    }

    fun getFakeTablesResponse(): Response<TableApiResponse> {
        return Response.success(tableApiResponse)
    }

    fun getFakeTeamResponse(): Response<Team> {
        return Response.success(team1)
    }

    private val match1 = Match(
        competition = Competition(
            id = 10,
            name = "Competition",
            code = "CO",
            type = "Group-Stage",
            emblem = "",
        ),
        id = 1,
        status = "TIMED",
        utcDate = "2023-01-15T18:30:00Z",
        homeTeam = team1,
        awayTeam = team2,
    )

    private val match2 = Match(
        competition = Competition(
            id = 10,
            name = "Competition",
            code = "CO",
            type = "Group-Stage",
            emblem = "",
        ),
        id = 2,
        status = "TIMED",
        utcDate = "2023-02-01T20:00:00Z",
        homeTeam = team2,
        awayTeam = team1,
    )

    private val matchApiResponse = MatchApiResponse(
        matches = listOf(match1, match2),
    )

    fun getFakeMatchesByTeamResponse(): Response<MatchApiResponse> {
        return Response.success(matchApiResponse)
    }
}
