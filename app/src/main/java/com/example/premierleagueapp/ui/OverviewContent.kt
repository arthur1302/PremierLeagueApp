package com.example.premierleagueapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.premierleagueapp.R
import com.example.premierleagueapp.ui.theme.PremierLeagueAppTheme

@Composable
fun OverviewContent(onBackPressed: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            // .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Logo and Name
        Box(
            modifier = Modifier
                .fillMaxWidth()
                // .clip(shape = MaterialTheme.shapes.large)
                .background(color = Color.Gray),
        ) {
            Image(
                painter = painterResource(R.drawable.england),
                contentDescription = null,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .alpha(0.5F),
                contentScale = ContentScale.Crop,
            )
            Image(
                painter = painterResource(R.drawable.city),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
            )
        }
        Text(
            text = "Manchester City",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(16.dp)
                .offset(y = (-95).dp),
            fontSize = 24.sp,
        )
        // Hier wordt de tekst "England" toegevoegd, ook links uitgelijnd
        Text(
            text = "England",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp)
                .offset(y = (-100).dp),
            fontSize = 18.sp,
        )

        LazyRow(
            modifier = Modifier.fillMaxSize().offset(y = (-75).dp),
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(data) { match ->
                UpcomingMatchCard(match)
            }
        }

        Text(text = teamData.coach.name)
        ScrollableGrid(teamData.coach, teamData.squad)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpcomingMatchCard(match: Match) {
    Card(
        modifier = Modifier.width(375.dp),
        onClick = { /* Handle match click */ },
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray,
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
                Image(
                    painter = painterResource(match.team1LogoResId),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                )
                Image(
                    painter = painterResource(match.team2LogoResId),
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
                        .padding(14.dp), // Aanpassen indien nodig voor de afstand tussen tekst en streepje
                    contentAlignment = Alignment.CenterStart,
                ) {
                    Text(text = "Manchester City", Modifier.align(Alignment.Center))
                }

                Text(text = "-", Modifier.align(Alignment.CenterVertically))

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(14.dp), // Aanpassen indien nodig voor de afstand tussen streepje en tekst
                    contentAlignment = Alignment.CenterEnd,
                ) {
                    Text(text = "Manchester United", Modifier.align(Alignment.Center))
                }
            }
            Text(
                text = match.date,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp),
            )
        }
    }
}

val test = listOf(
    "test1",
    "trst2",
)

@Composable
fun ScrollableGrid(coach: Coach, squad: List<Player>) {
    LazyRow() {
        item {
            CoachCard(coach, isCoach = true)
        }
        items(squad) { player ->
            PlayerCard(player, isCoach = false)
        }
    }
    // GRID WERKT NIET
    /*LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(test) { string ->
            Text(text = string)
        }
    }*/
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerCard(player: Player, isCoach: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = { /* Handle player click */ },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Text(
                text = if (isCoach) "C" else player.shirtNumber.toString(),
                modifier = Modifier.padding(bottom = 4.dp),
            )
            Text(text = player.name)
            Text(text = player.nationality)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoachCard(coach: Coach, isCoach: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = { /* Handle player click */ },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Text(
                text = "C",
                modifier = Modifier.padding(bottom = 4.dp),
            )
            Text(text = coach.name)
            Text(text = coach.nationality)
        }
    }
}
data class Match(
    val team1LogoResId: Int,
    val team2LogoResId: Int,
    val team1Name: String,
    val team2Name: String,
    val date: String,
)

val data = listOf(
    Match(
        team1LogoResId = R.drawable.chelsea,
        team2LogoResId = R.drawable.city,
        team1Name = "Team A",
        team2Name = "Team B",
        date = "2023-01-01",
    ),
    Match(
        team1LogoResId = R.drawable.united,
        team2LogoResId = R.drawable.city,
        team1Name = "Team C",
        team2Name = "Team D",
        date = "2023-02-15",
    ),
    Match(
        team1LogoResId = R.drawable.liverpool,
        team2LogoResId = R.drawable.city,
        team1Name = "Team E",
        team2Name = "Team F",
        date = "2023-03-20",
    ),
    Match(
        team1LogoResId = R.drawable.chelsea,
        team2LogoResId = R.drawable.city,
        team1Name = "Team G",
        team2Name = "Team H",
        date = "2023-04-05",
    ),
    Match(
        team1LogoResId = R.drawable.chelsea,
        team2LogoResId = R.drawable.city,
        team1Name = "Team I",
        team2Name = "Team J",
        date = "2023-05-10",
    ),
)

data class Player(
    val name: String,
    val nationality: String,
    val shirtNumber: Int,
)

data class Coach(
    val name: String,
    val nationality: String,
)

data class TeamData(
    val coach: Coach,
    val squad: List<Player>,
)

// Gebruik van de composable
val teamData = TeamData(
    coach = Coach(name = "Coach Name", nationality = "Nationality"),
    squad = listOf(
        Player(name = "Player 1", nationality = "Nationality 1", shirtNumber = 10),
        Player(name = "Player 2", nationality = "Nationality 2", shirtNumber = 7),
        Player(name = "Player 3", nationality = "Nationality 2", shirtNumber = 7),
    ),
)

@Preview(showBackground = true)
@Composable
fun OverviewPrev() {
    PremierLeagueAppTheme {
        OverviewContent(onBackPressed = {})
    }
}