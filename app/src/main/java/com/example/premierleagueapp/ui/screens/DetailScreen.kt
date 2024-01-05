package com.example.premierleagueapp.ui.screens

import android.content.res.Configuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.premierleagueapp.R
import com.example.premierleagueapp.ui.TeamApiDetailState
import com.example.premierleagueapp.ui.components.detailScreen.DetailContentLandscape
import com.example.premierleagueapp.ui.components.detailScreen.DetailContentPortrait
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
                DetailContentPortrait(team, matchApiState, viewModel)
            } else {
                DetailContentLandscape(team, matchApiState, viewModel)
            }
        }
        TeamApiDetailState.Error -> Text(stringResource(R.string.error_fetching_team))
        TeamApiDetailState.Loading -> Text(stringResource(R.string.loading_team))
    }
}
