package com.example.premierleagueapp.ui.components.detailScreen.teamDetails.header

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import coil.compose.rememberImagePainter
import com.example.premierleagueapp.R
import com.example.premierleagueapp.model.Team

@Composable
fun DetailHeader(team: Team?) {
    val painter: Painter = rememberImagePainter(R.drawable.england)
    val orientation = LocalConfiguration.current.orientation

    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        DetailHeaderPortrait(painter, team)
    } else {
        DetailHeaderLandscape(team, painter)
    }
}

