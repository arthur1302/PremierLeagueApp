package com.example.premierleagueapp.ui.components.startScreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.premierleagueapp.model.Team

/**
 * Composable that represents a [LazyColumn] of all [TeamCard] items
 *
 * @author Arthur Haus
 *
 * @param teams [List] of [Team]
 * @param onTeamClick [Unit]
 * @param lazyListState [LazyListState]
 */
@Composable
fun TeamsList(teams: List<Team>, onTeamClick: (teamId: Int) -> Unit, lazyListState: LazyListState) {
    LazyColumn(state = lazyListState) {
        items(teams) { team ->
            TeamCard(
                name = team.name,
                shortName = team.shortName,
                imageUrl = team.crest,
                onClick = { onTeamClick(team.id) },
            )
        }
    }
}
