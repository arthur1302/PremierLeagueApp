package com.example.premierleagueapp.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.example.premierleagueapp.network.Team

@Composable
fun TeamsList(teamApiState: TeamApiState, uiListState: List<Team>, onTeamClick: (teamId: Int) -> Unit, lazyListState: LazyListState) {
    LazyColumn(state = lazyListState) {
        items(uiListState) { team ->
            TeamCard(
                name = team.name,
                country = team.shortName,
                imageUrl = team.crest,
                onClick = { onTeamClick(team.id) },
            )
        }
    }
}
