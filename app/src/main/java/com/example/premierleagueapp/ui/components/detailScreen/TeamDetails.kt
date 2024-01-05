package com.example.premierleagueapp.ui.components.detailScreen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
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
        val orientation = LocalConfiguration.current.orientation

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Column {
                Text(stringResource(R.string.coach_title), Modifier.align(Alignment.Start))
                Text(text = team!!.coach.name, Modifier.align(Alignment.Start))
                Text(text = team.coach.nationality, Modifier.align(Alignment.Start))
            }
            Column {
                Text(stringResource(R.string.stadium_title), Modifier.align(Alignment.End))
                Text(text = team!!.venue, Modifier.align(Alignment.End))
            } } else {
            Column {
                Text(stringResource(R.string.coach_title), Modifier.align(Alignment.Start), style = MaterialTheme.typography.bodyMedium)
                Text(text = team!!.coach.name, Modifier.align(Alignment.Start), style = MaterialTheme.typography.bodyMedium)
                Text(text = team.coach.nationality, Modifier.align(Alignment.Start), style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(12.dp))
                Text(stringResource(R.string.stadium_title), Modifier.align(Alignment.Start), style = MaterialTheme.typography.bodyMedium)
                Text(text = team!!.venue, Modifier.align(Alignment.Start), style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
