package com.example.premierleagueapp.ui.components.detailScreen.teamDetails.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.premierleagueapp.model.Team

/**
 * Composable that contains the details of a specific [Team]
 *
 * @author Arthur Haus
 *
 * @param team [Team]
 *
 */
@Composable
fun TeamDetails(team: Team?) {
    Row(
        Modifier
            .padding(16.dp, 8.dp, 16.dp, 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        val orientation = LocalConfiguration.current.orientation

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            TeamDetailsPortrait(team)
        } else {
            TeamDetailsLandscape(team)
        }
    }
}
