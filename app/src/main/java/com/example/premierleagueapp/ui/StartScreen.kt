package com.example.premierleagueapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun StartScreen(
    overview: Boolean,
    lazyListState: LazyListState,
    onOverviewChanged: (Boolean) -> Unit,
) {
    val viewModel: TeamViewModel = viewModel()
    val teamUiState by viewModel.teamUiState.collectAsState()
    val teams = teamUiState.teams

    Box() {
        if (overview) {
            OverviewContent(onBackPressed = { onOverviewChanged(false) })
        } else {
            TeamsList(teams, onTeamClick = { onOverviewChanged(true) }, lazyListState)
        }
    }
}
