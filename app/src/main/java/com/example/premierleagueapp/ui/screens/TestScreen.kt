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
import com.example.premierleagueapp.ui.TableApiState
import com.example.premierleagueapp.ui.components.Ranking
import com.example.premierleagueapp.ui.viewmodels.SoccerViewModel

@Composable
fun TestScreen(lazyListState: LazyListState) {
    val viewModel: SoccerViewModel = viewModel(factory = SoccerViewModel.Factory)
    val tableApiState = viewModel.tableApiState

    when (tableApiState) {
        is TableApiState.Loading -> {
            Text(stringResource(R.string.loading_teams))
        }
        is TableApiState.Error -> {
            Text(stringResource(R.string.error_fetching_teams))
        }
        is TableApiState.Success -> {
            val uiTableState by viewModel.uiTableState.collectAsState()
            Box {
                Ranking(uiTableState, lazyListState)
            }
        }
    }
}
