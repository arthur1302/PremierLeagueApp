package com.example.premierleagueapp

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import data.Team

@Composable
fun TeamsList(teams: List<Team>, onTeamClick: () -> Unit, lazyListState: LazyListState) {
    LazyColumn(state = lazyListState) {
        items(teams) {
            TeamCard(
                name = it.name,
                country = it.country,
                imageResourceId = it.imageResourceId,
                onClick = onTeamClick,
            )
        }
    }
}
