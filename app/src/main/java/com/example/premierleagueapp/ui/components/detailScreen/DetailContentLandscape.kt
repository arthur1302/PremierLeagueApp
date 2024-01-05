package com.example.premierleagueapp.ui.components.detailScreen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.premierleagueapp.model.Team
import com.example.premierleagueapp.ui.MatchApiState
import com.example.premierleagueapp.ui.components.detailScreen.matchList.MatchList
import com.example.premierleagueapp.ui.components.detailScreen.squadList.SquadList
import com.example.premierleagueapp.ui.components.detailScreen.teamDetails.details.TeamDetails
import com.example.premierleagueapp.ui.components.detailScreen.teamDetails.header.DetailHeader
import com.example.premierleagueapp.ui.viewmodels.TeamDetailsViewModel

@Composable
fun DetailContentLandscape(
    team: Team?,
    matchApiState: MatchApiState,
    viewModel: TeamDetailsViewModel,
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .weight(0.75f)
                .fillMaxHeight()
                .padding(8.dp, 8.dp, 2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DetailHeader(team)
            TeamDetails(team)
        }

        Column(
            modifier = Modifier
                .weight(1.55f)
                .fillMaxHeight()
                .padding(2.dp, 8.dp, 2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MatchList(matchApiState, viewModel)
        }
        Column(
            modifier = Modifier
                .weight(0.70f)
                .fillMaxHeight()
                .padding(2.dp, 8.dp, 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SquadList(team?.squad, team?.crest)
        }
    }
}
