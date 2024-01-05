package com.example.premierleagueapp.ui.components.detailScreen.matchList

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.premierleagueapp.R
import com.example.premierleagueapp.ui.MatchApiState
import com.example.premierleagueapp.ui.viewmodels.TeamDetailsViewModel
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

/**
 * Composable that contains a LazyRow of upcomingMatchCards with the 5 upcoming matches of a team
 *
 * @author Arthur Haus
 *
 * @param matchApiState gets an apiState of the viewmodel
 * @param viewModel gets an instance of the [TeamDetailsViewModel]
 *
 */
@OptIn(ExperimentalSnapperApi::class)
@Composable
fun MatchList(
    matchApiState: MatchApiState,
    viewModel: TeamDetailsViewModel,
) {
    when (matchApiState) {
        is MatchApiState.Success -> {
            val uiMatchListState by viewModel.uiMatchListState.collectAsState()
            val matches = uiMatchListState
            val lazyListState = rememberLazyListState()
            LazyRow(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 6.dp),
                contentPadding = PaddingValues(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                state = lazyListState,
                verticalAlignment = Alignment.CenterVertically,
                flingBehavior = rememberSnapperFlingBehavior(lazyListState),
            ) {
                items(matches) { match ->
                    UpcomingMatchCard(match)
                }
            }
        }

        MatchApiState.Error -> Log.i("ERROR", stringResource(R.string.error_fetching_matches))
        MatchApiState.Loading -> Log.i("Loading", stringResource(R.string.loading_matches))
    }
}
