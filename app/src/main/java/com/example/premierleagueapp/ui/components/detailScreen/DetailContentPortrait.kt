package com.example.premierleagueapp.ui.components.detailScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.premierleagueapp.model.Team
import com.example.premierleagueapp.ui.MatchApiState
import com.example.premierleagueapp.ui.components.detailScreen.matchList.MatchList
import com.example.premierleagueapp.ui.components.detailScreen.squadList.SquadList
import com.example.premierleagueapp.ui.components.detailScreen.teamDetails.details.TeamDetails
import com.example.premierleagueapp.ui.components.detailScreen.teamDetails.header.DetailHeader
import com.example.premierleagueapp.ui.viewmodels.TeamDetailsViewModel

/**
 * Composable that contains the detail content of a specific [Team] for portrait mode
 *
 * @author Arthur Haus
 *
 * @param team [Team]
 * @param matchApiState [MatchApiState]
 * @param viewModel [TeamDetailsViewModel]
 */
@Composable
fun DetailContentPortrait(
    team: Team?,
    matchApiState: MatchApiState,
    viewModel: TeamDetailsViewModel,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DetailHeader(team)
        TeamDetails(team)
        MatchList(matchApiState, viewModel)
        SquadList(team?.squad, team?.crest)
    }
}
