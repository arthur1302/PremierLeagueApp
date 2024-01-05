package com.example.premierleagueapp.ui.components.detailScreen.teamDetails.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.premierleagueapp.R
import com.example.premierleagueapp.model.Team
import com.example.premierleagueapp.ui.components.helpers.imageHandler

/**
 * Composable that contains the header of a specific [Team] for portrait mode
 *
 * @author Arthur Haus
 *
 * @param team [Team]
 * @param painter [Painter]
 */
@Composable
fun DetailHeaderPortrait(
    painter: Painter,
    team: Team?,
) {
    var painterCrest = painter
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Gray),
    ) {
        Image(
            painter = painterResource(R.drawable.england),
            contentDescription = null,
            modifier = Modifier
                .height(115.dp)
                .fillMaxWidth()
                .alpha(0.5F),
            contentScale = ContentScale.Crop,
        )
        painterCrest = imageHandler(team?.crest!!, painterCrest)
        Image(
            painter = painterCrest,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.BottomEnd)
                .padding(16.dp),
        )
        Text(
            text = team.name,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .offset(y = 25.dp),
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = team.tla,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 16.dp)
                .offset(y = 85.dp),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}
