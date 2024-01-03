package com.example.premierleagueapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.premierleagueapp.R
import com.example.premierleagueapp.ui.DetailHeader
import com.example.premierleagueapp.ui.MatchList
import com.example.premierleagueapp.ui.SoccerViewModel
import com.example.premierleagueapp.ui.SquadList
import com.example.premierleagueapp.ui.TeamApiDetailState
import com.example.premierleagueapp.ui.TeamDetails
import com.example.premierleagueapp.ui.theme.PremierLeagueAppTheme

@Composable
fun DetailScreen(teamId: Int) {
    val viewModel: SoccerViewModel = viewModel(factory = SoccerViewModel.Factory)
    val teamApiDetailState = viewModel.teamApiDetailState
    val matchApiState = viewModel.matchApiState

    LaunchedEffect(teamId) {
        viewModel.getSingleTeam(teamId)
        viewModel.getMatchesByTeam(teamId)
    }

    when (teamApiDetailState) {
        is TeamApiDetailState.Success -> {
            val uiTeamState by viewModel.uiTeamState.collectAsState()
            val team = uiTeamState
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                DetailHeader(team)
                TeamDetails(team)
                MatchList(matchApiState, viewModel)
                SquadList(team?.squad, team?.crest)
            }
        }
        TeamApiDetailState.Error -> Text(stringResource(R.string.error_fetching_team))
        TeamApiDetailState.Loading -> Text(stringResource(R.string.loading_team))
    }
}

@Preview(showBackground = true)
@Composable
fun OverviewPrev() {
    PremierLeagueAppTheme {
        // DetailScreen()
    }
}
