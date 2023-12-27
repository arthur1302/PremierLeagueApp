package com.example.premierleagueapp

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import data.Team

@Composable
fun StartScreen(
    overview: Boolean,
    teams: SnapshotStateList<Team>,
    onOverviewChanged: (Boolean) -> Unit,
) {
    Box() {
        if (overview) {
            OverviewContent(onBackPressed = { onOverviewChanged(false) })
        } else {
            TeamsList(teams, onTeamClick = { onOverviewChanged(true) })
        }
    }
}
