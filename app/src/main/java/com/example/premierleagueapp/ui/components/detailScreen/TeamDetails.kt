package com.example.premierleagueapp.ui.components.detailScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.premierleagueapp.R
import com.example.premierleagueapp.model.Team

@Composable
fun TeamDetails(team: Team?) {
    Row(
        Modifier
            .padding(16.dp, 8.dp, 16.dp, 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
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
}