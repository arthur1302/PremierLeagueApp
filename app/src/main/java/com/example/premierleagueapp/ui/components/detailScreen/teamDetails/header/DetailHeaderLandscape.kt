package com.example.premierleagueapp.ui.components.detailScreen.teamDetails.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.premierleagueapp.model.Team
import com.example.premierleagueapp.ui.components.helpers.imageHandler

@Composable
fun DetailHeaderLandscape(
    team: Team?,
    painter: Painter,
) {
    var painterCrest = painter
    Row(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Column(Modifier.weight(1.5f)) {
            Text(
                text = team!!.shortName,
                modifier = Modifier
                    .padding(16.dp, 16.dp, 4.dp, 4.dp),
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = team.tla,
                modifier = Modifier
                    .padding(start = 16.dp),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Column(Modifier.weight(1f)) {
            painterCrest = imageHandler(team?.crest!!, painterCrest)
            Image(
                painter = painterCrest,
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
                    .padding(12.dp),
            )
        }
    }
}
