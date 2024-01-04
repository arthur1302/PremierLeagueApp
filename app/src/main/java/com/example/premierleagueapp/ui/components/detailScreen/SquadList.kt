package com.example.premierleagueapp.ui.components.detailScreen

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.premierleagueapp.model.Player

@Composable
fun SquadList(squad: List<Player>?, crest: String?) {
    LazyRow {
        items(squad!!) { player ->
            PlayerCard(player, crest!!)
        }
    }
}
