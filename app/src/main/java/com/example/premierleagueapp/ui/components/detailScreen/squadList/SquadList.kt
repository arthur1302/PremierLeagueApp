package com.example.premierleagueapp.ui.components.detailScreen.squadList

import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.example.premierleagueapp.model.Player

@Composable
fun SquadList(squad: List<Player>?, crest: String?) {
    val orientation = LocalConfiguration.current.orientation

    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        LazyRow {
            items(squad!!) { player ->
                PlayerCard(player, crest!!)
            }
        }
    } else {
        LazyColumn {
            items(squad!!) { player ->
                PlayerCard(player, crest!!)
            }
        }
    }
}
