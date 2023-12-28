package com.example.premierleagueapp.ui

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.premierleagueapp.R
import com.example.premierleagueapp.ui.theme.PremierLeagueAppTheme
import kotlinx.coroutines.launch

enum class Destinations {
    Start,
    About,
    Contact,
    Overview,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PremierLeagueApp() {
    var overview by rememberSaveable { mutableStateOf(false) }
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val emailLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { _ ->
    }
    Scaffold(
        topBar = {
            PremierLeagueTopBar(
                {
                    val isStartDestination = currentBackStackEntry?.destination?.route == Destinations.Start.name
                    if (isStartDestination) {
                        IconButton(onClick = {
                            // show menu
                        }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu button")
                        }
                    } else {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back button")
                        }
                    }
                },
                when (currentBackStackEntry?.destination?.route) {
                    Destinations.Contact.name -> R.string.contact_title
                    Destinations.About.name -> R.string.about_title
                    else -> { R.string.app_title }
                },
            )
        },
        bottomBar =
        {
            PremierLeagueAppBottomBar(
                { navController.popBackStack(Destinations.Start.name, false) },
                { navController.navigate(Destinations.Contact.name) },
                { navController.navigate(Destinations.About.name) },
            )
        },
        floatingActionButton = {
            when (currentBackStackEntry?.destination?.route) {
                Destinations.Start.name -> {
                    FloatingActionButton(onClick = {
                        coroutineScope.launch { lazyListState.animateScrollToItem(0) }
                    }) {
                        Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Scroll up")
                    }
                }
                Destinations.Contact.name -> {
                    FloatingActionButton(onClick = {
                    }) {
                        Icon(Icons.Default.Email, contentDescription = "Send mail")
                    }
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Destinations.Start.name,
            Modifier.padding(innerPadding),
        ) {
            composable(Destinations.Start.name) {
                StartScreen(lazyListState) { teamId ->
                    navController.navigate("${Destinations.Overview.name}/$teamId")
                }
            }
            composable(Destinations.Contact.name) {
                Text("My contact info")
            }
            composable(Destinations.About.name) {
                Text("My about info")
            }
            composable(Destinations.Overview.name) {
                //OverviewContent()
            }
            composable(Destinations.Overview.name + "/{teamId}") { backStackEntry ->
                val teamId = backStackEntry.arguments?.getString("teamId")?.toIntOrNull()
                if (teamId != null) {
                    OverviewContent(teamId)
                } else {
                    // Handle foutieve of ontbrekende teamId
                }
            }
        }
    }
}

@Preview
@Composable
fun PremierLeagueAppPreview() {
    PremierLeagueAppTheme {
        PremierLeagueApp()
    }
}
