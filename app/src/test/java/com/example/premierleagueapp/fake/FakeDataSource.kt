package com.example.premierleagueapp.fake

import com.example.premierleagueapp.model.Coach
import com.example.premierleagueapp.model.Competition
import com.example.premierleagueapp.model.MatchApiResponse
import com.example.premierleagueapp.model.Player
import com.example.premierleagueapp.model.Season
import com.example.premierleagueapp.model.TeamApiResponse
import com.example.premierleagueapp.network.Match
import com.example.premierleagueapp.network.Team
import retrofit2.Response

object FakeDataSource {

    val team1 = Team(
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

    val team2 = Team(
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

    val teamApiResponse = TeamApiResponse(
        count = 2,
        season = Season(
            id = 123,
            startDate = "2023-01-01",
            endDate = "2023-12-31",
            currentMatchday = 20,
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

    fun getFakeTeamsResponse(): Response<TeamApiResponse> {
        return Response.success(teamApiResponse)
    }

    fun getFakeTeamResponse(): Response<Team> {
        return Response.success(team1)
    }

    val match1 = Match(
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

    val match2 = Match(
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

    val matchApiResponse = MatchApiResponse(
        matches = listOf(match1, match2),
    )

    fun getFakeMatchesByTeamResponse(): Response<MatchApiResponse> {
        return Response.success(matchApiResponse)
    }
}
