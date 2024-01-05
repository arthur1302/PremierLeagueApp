package com.example.premierleagueapp.ui.components.detailScreen.teamDetails.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.premierleagueapp.R
import com.example.premierleagueapp.model.Team

@Composable
fun TeamDetailsLandscape(team: Team?) {
    Column {
        Text(
            stringResource(R.string.coach_title),
            Modifier.align(Alignment.Start),
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            text = team!!.coach.name,
            Modifier.align(Alignment.Start),
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            text = team.coach.nationality,
            Modifier.align(Alignment.Start),
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(Modifier.height(12.dp))
        Text(
            stringResource(R.string.stadium_title),
            Modifier.align(Alignment.Start),
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            text = team.venue,
            Modifier.align(Alignment.Start),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}
