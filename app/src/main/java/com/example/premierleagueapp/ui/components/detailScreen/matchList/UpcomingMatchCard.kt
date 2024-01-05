package com.example.premierleagueapp.ui.components.detailScreen.matchList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.premierleagueapp.R
import com.example.premierleagueapp.model.Match
import com.example.premierleagueapp.ui.components.helpers.imageHandler
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

/**
 * Composable that contains the information about an upcoming match
 *
 * @author Arthur Haus
 *
 * @param match represents a specific match
 */
@Composable
fun UpcomingMatchCard(match: Match) {
    var painter: Painter = rememberImagePainter(R.drawable.england)
    Card(
        modifier = Modifier.width(375.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                painter = imageHandler(match.homeTeam.crest, painter)
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                )
                painter = imageHandler(match.awayTeam.crest, painter)
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(14.dp),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    Text(text = match.homeTeam.shortName, Modifier.align(Alignment.Center))
                }

                Text(text = "-", Modifier.align(Alignment.CenterVertically))

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(14.dp),
                    contentAlignment = Alignment.CenterEnd,
                ) {
                    Text(text = match.awayTeam.shortName, Modifier.align(Alignment.Center))
                }
            }
            Text(
                text = match.competition.name,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp),
            )
            Text(
                text = formatUtcDate(match.utcDate),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp),
            )
        }
    }
}

/**
 * Function that formats the date String
 *
 * @param utcDateString [String]
 * @return [String]
 */
fun formatUtcDate(utcDateString: String): String {
    val utcFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    utcFormat.timeZone = TimeZone.getTimeZone("UTC")

    val localFormat = SimpleDateFormat("dd-MM-yyyy | HH'h'mm", Locale.getDefault())
    localFormat.timeZone = TimeZone.getDefault()

    return try {
        val utcDate = utcFormat.parse(utcDateString)
        localFormat.format(utcDate!!)
    } catch (e: Exception) {
        R.string.invalid_date.toString()
    }
}
