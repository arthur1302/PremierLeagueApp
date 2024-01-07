package com.example.premierleagueapp.ui.components.detailScreen.squadList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.premierleagueapp.R
import com.example.premierleagueapp.model.Player
import com.example.premierleagueapp.ui.components.helpers.imageHandler

/**
 * Composable that contains the content for a specific player
 *
 * @author Arthur Haus
 *
 * @param player represents the specific [Player] for whom we want to make a card
 * @param crest represents the crest of the team for which the player is playing
 */
@Composable
fun PlayerCard(player: Player, crest: String) {
    var painter: Painter = rememberImagePainter(R.drawable.england)
    Card(
        modifier = Modifier
            .width(175.dp)
            .height(175.dp)
            .padding(8.dp, 8.dp, 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = when (player.position) {
                    "Goalkeeper" -> stringResource(R.string.goalkeeper_abrv)
                    "Defence" -> stringResource(R.string.defender_abrv)
                    "Midfield" -> stringResource(R.string.midfielder_abrv)
                    "Offence" -> stringResource(R.string.attacker_abrv)
                    else -> stringResource(R.string.non_available_abrv)
                },
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(bottom = 8.dp).align(Alignment.CenterVertically)
                    .alpha(0.5F),
            )
            painter = imageHandler(crest, painter)
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.size(65.dp).align(Alignment.CenterVertically).padding(12.dp),
            )
        }
        Column(Modifier.padding(start = 8.dp, end = 8.dp)) {
            Text(text = player.name)
            Text(text = player.nationality)
        }
    }
}
