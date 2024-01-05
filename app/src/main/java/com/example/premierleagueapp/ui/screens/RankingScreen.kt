package com.example.premierleagueapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.premierleagueapp.R
import com.example.premierleagueapp.ui.TableApiState
import com.example.premierleagueapp.ui.components.rankingScreen.Ranking
import com.example.premierleagueapp.ui.viewmodels.RankingViewModel

/**
 * Composable for the ranking screen
 *
 * @author Arthur Haus
 *
 * @param lazyListState [LazyListState]
 */
@Composable
fun RankingScreen(lazyListState: LazyListState) {
    val viewModel: RankingViewModel = viewModel(factory = RankingViewModel.Factory)

    when (viewModel.tableApiState) {
        is TableApiState.Loading -> {
            Text(stringResource(R.string.loading_teams))
        }
        is TableApiState.Error -> {
            Text(stringResource(R.string.error_fetching_teams))
        }
        is TableApiState.Success -> {
            val uiTableState by viewModel.uiTableState.collectAsState()
            Box(Modifier.padding(top = 8.dp)) {
                Ranking(uiTableState, lazyListState)
            }
        }
    }
}
