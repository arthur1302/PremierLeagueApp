package com.example.premierleagueapp

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import data.Team

@Composable
fun TeamsList(teams: List<Team>, onTeamClick: () -> Unit) {
    Column() {
        for (team in teams) {
            TeamCard(
                name = team.name,
                country = team.country,
                imageResourceId = team.imageResourceId,
                onClick = onTeamClick,
            )
        }
    }
}