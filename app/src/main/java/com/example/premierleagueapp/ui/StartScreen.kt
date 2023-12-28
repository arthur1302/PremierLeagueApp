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
    val viewModel: TeamViewModel = viewModel()
    val teamApiState = viewModel.teamApiState

    when (teamApiState) {
        is TeamApiState.Loading -> {
            Text("Loading teams...")
        }

        is TeamApiState.Error -> {
            Text("Error while loading teams...")
        }
        is TeamApiState.Succes -> {
            Box() {
                TeamsList(teamApiState.teams, onTeamClick = { teamId ->
                    onTeamClick(teamId)
                }, lazyListState)
            }
        }
    }


}
