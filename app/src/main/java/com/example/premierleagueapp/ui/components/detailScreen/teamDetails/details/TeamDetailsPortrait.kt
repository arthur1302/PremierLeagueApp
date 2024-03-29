package com.example.premierleagueapp.ui.components.detailScreen.teamDetails.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.premierleagueapp.R
import com.example.premierleagueapp.model.Team

/**
 * Composable that contains the details of a specific [Team] for portrait mode
 *
 * @author Arthur Haus
 *
 * @param team [Team]
 */
@Composable
fun TeamDetailsPortrait(team: Team?) {
    Column {
        Text(stringResource(R.string.coach_title), Modifier.align(Alignment.Start))
        Text(text = team!!.coach.name, Modifier.align(Alignment.Start))
        Text(text = team.coach.nationality, Modifier.align(Alignment.Start))
    }
    Column {
        Text(stringResource(R.string.stadium_title), Modifier.align(Alignment.End))
        Text(text = team!!.venue, Modifier.align(Alignment.End))
    }
}
