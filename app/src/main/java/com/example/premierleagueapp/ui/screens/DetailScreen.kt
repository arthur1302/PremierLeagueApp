package com.example.premierleagueapp.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.premierleagueapp.R
import com.example.premierleagueapp.ui.TeamApiDetailState
import com.example.premierleagueapp.ui.components.detailScreen.DetailHeader
import com.example.premierleagueapp.ui.components.detailScreen.MatchList
import com.example.premierleagueapp.ui.components.detailScreen.SquadList
import com.example.premierleagueapp.ui.components.detailScreen.TeamDetails
import com.example.premierleagueapp.ui.theme.PremierLeagueAppTheme
import com.example.premierleagueapp.ui.viewmodels.TeamDetailsViewModel

@Composable
fun DetailScreen(teamId: Int) {
    val viewModel: TeamDetailsViewModel = viewModel(factory = TeamDetailsViewModel.Factory)
    val teamApiDetailState = viewModel.teamApiDetailState
    val matchApiState = viewModel.matchApiState

    LaunchedEffect(teamId) {
        viewModel.getSingleTeam(teamId)
        viewModel.getMatchesByTeam(teamId)
    }

    val orientation = LocalConfiguration.current.orientation

    when (teamApiDetailState) {
        is TeamApiDetailState.Success -> {
            val uiTeamState by viewModel.uiTeamState.collectAsState()
            val team = uiTeamState

            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                // Lay-out voor portretmodus
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    DetailHeader(team)
                    TeamDetails(team)
                    MatchList(matchApiState, viewModel)
                    SquadList(team?.squad, team?.crest)
                }
            } else {
                // Lay-out voor landschapsmodus
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        modifier = Modifier
                            .weight(0.75f)
                            .fillMaxHeight()
                            .padding(8.dp, 8.dp, 2.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        DetailHeader(team)
                        TeamDetails(team)
                    }

                    Column(
                        modifier = Modifier
                            .weight(1.55f)
                            .fillMaxHeight()
                            .padding(2.dp, 8.dp, 2.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        MatchList(matchApiState, viewModel)
                    }
                    Column(
                        modifier = Modifier
                            .weight(0.70f)
                            .fillMaxHeight()
                            .padding(2.dp, 8.dp, 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        SquadList(team?.squad, team?.crest)
                    }
                }
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
