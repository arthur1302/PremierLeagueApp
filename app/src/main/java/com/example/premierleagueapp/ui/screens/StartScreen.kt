package com.example.premierleagueapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.premierleagueapp.R
import com.example.premierleagueapp.ui.TeamApiState
import com.example.premierleagueapp.ui.components.startScreen.TeamsList
import com.example.premierleagueapp.ui.viewmodels.SoccerViewModel

@Composable
fun StartScreen(
    lazyListState: LazyListState,
    onTeamClick: (teamId: Int) -> Unit,
) {
    val viewModel: SoccerViewModel = viewModel(factory = SoccerViewModel.Factory)
    val teamApiState = viewModel.teamApiState

    when (teamApiState) {
        is TeamApiState.Loading -> {
            Text(stringResource(R.string.loading_teams))
        }
        is TeamApiState.Error -> {
            Text(stringResource(R.string.error_fetching_teams))
        }
        is TeamApiState.Success -> {
            val uiListState by viewModel.uiListState.collectAsState()
            Box {
                TeamsList(uiListState, onTeamClick = { teamId ->
                    onTeamClick(teamId)
                }, lazyListState)
            }
        }
    }
}
