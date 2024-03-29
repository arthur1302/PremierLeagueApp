package com.example.premierleagueapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.premierleagueapp.R
import com.example.premierleagueapp.ui.components.general.PremierLeagueAppBottomBar
import com.example.premierleagueapp.ui.components.general.PremierLeagueTopBar
import com.example.premierleagueapp.ui.screens.AboutScreen
import com.example.premierleagueapp.ui.screens.ContactScreen
import com.example.premierleagueapp.ui.screens.DetailScreen
import com.example.premierleagueapp.ui.screens.RankingScreen
import com.example.premierleagueapp.ui.screens.StartScreen
import com.example.premierleagueapp.ui.theme.PremierLeagueAppTheme

/**
 * Enum class that contains all the route destinations
 */
enum class Destinations {
    Start,
    About,
    Contact,
    Overview,
    Ranking,
}

/**
 * Composable that contains the whole application
 *
 * @author Arthur Haus
 *
 * @param navController [NavHostController]
 */

@SuppressLint("QueryPermissionsNeeded")
@Composable
fun PremierLeagueApp(navController: NavHostController = rememberNavController()) {
    // Contains the backstack for navigation
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // Main layout component
    Scaffold(
        topBar = {
            PremierLeagueTopBar(
                {
                    val isStartDestination = currentBackStackEntry?.destination?.route == Destinations.Start.name
                    if (!isStartDestination) {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = stringResource(R.string.back_button))
                        }
                    }
                },
                // The title will change, depending on which route is active
                when (currentBackStackEntry?.destination?.route) {
                    Destinations.Contact.name -> R.string.contact_title
                    Destinations.About.name -> R.string.about_title
                    Destinations.Overview.name -> R.string.team_title
                    Destinations.Ranking.name -> R.string.ranking_title
                    else -> { R.string.app_title }
                },
            )
        },
        bottomBar =
        {
            PremierLeagueAppBottomBar(
                currentBackStackEntry,
                coroutineScope,
                lazyListState,
                { navController.popBackStack(Destinations.Start.name, false) },
                {
                    navController.navigate(Destinations.Contact.name) {
                        launchSingleTop = true
                    }
                },
                {
                    navController.navigate(Destinations.About.name) {
                        launchSingleTop = true
                    }
                },
                {
                    navController.navigate(Destinations.Ranking.name) {
                        launchSingleTop = true
                    }
                },
            )
        },
        containerColor = MaterialTheme.colorScheme.onError,
    ) { innerPadding ->
        // Contains all the possible routes
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
                ContactScreen()
            }
            composable(Destinations.About.name) {
                AboutScreen()
            }
            composable(Destinations.Overview.name + "/{teamId}") { backStackEntry ->
                val teamId = backStackEntry.arguments?.getString("teamId")?.toIntOrNull()
                if (teamId != null) {
                    DetailScreen(teamId)
                } else {
                    Text(stringResource(R.string.id_value_null))
                    Button(
                        onClick = { navController.navigate(Destinations.Start.name) },
                    ) {
                        Text(stringResource(R.string.home_button_no_id))
                    }
                }
            }
            composable(Destinations.Ranking.name) {
                RankingScreen(lazyListState)
            }
        }
    }
}

/**
 * Composable for a preview of the application
 */
@Preview
@Composable
fun PremierLeagueAppPreview() {
    PremierLeagueAppTheme {
        PremierLeagueApp()
    }
}
