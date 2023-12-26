package com.example.premierleagueapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.smallTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.premierleagueapp.ui.theme.PremierLeagueAppTheme
import data.Team

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PremierLeagueAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun TeamCard(name: String = "", country: String = "", imageResourceId: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(color = Color.White)
            .padding(12.dp)
            .border(0.1.dp, Color.Black, shape = CircleShape)
            .fillMaxWidth(),
    ) {
        Row(modifier = modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = imageResourceId),
                contentDescription = null,
                modifier = modifier
                    .size(50.dp)
                    .background(shape = CircleShape, color = Color.White)
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically),
            )
            Column(modifier = modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                Text(name, fontSize = 16.sp)
                Text(country, fontSize = 12.sp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldEx() {
    var presses by remember { mutableStateOf(0) }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Premier League")
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { presses++ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            val team = Team.getOne()
            TeamCard(name = team.name, country = team.country, image = team.image)
        }
    }
}

@Preview
@Composable
fun ScaffoldPreview() {
    PremierLeagueAppTheme {
        ScaffoldEx()
    }
}

@Composable
fun TeamList() {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
    ) {
        TeamCard()
        TeamCard()
    }
}

@Preview
@Composable
fun TeamPreview() {
    PremierLeagueAppTheme {
        TeamList()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}
