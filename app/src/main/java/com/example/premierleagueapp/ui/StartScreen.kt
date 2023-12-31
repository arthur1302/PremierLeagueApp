package com.example.premierleagueapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun StartScreen(
    lazyListState: LazyListState,
    onTeamClick: (teamId: Int) -> Unit,
) {
    val viewModel: SoccerViewModel = viewModel(factory = SoccerViewModel.Factory)
    val teamApiState = viewModel.teamApiState
    val uiListState by viewModel.uiListState.collectAsState()
    when (teamApiState) {
        is TeamApiState.Loading -> {
            Text("Loading teams...")
        }

        is TeamApiState.Error -> {
            Text("Error while loading teams...")
        }
        is TeamApiState.Success -> {
            Box() {
                TeamsList(uiListState, onTeamClick = { teamId ->
                    onTeamClick(teamId)
                }, lazyListState)
            }
        }
    }
}
