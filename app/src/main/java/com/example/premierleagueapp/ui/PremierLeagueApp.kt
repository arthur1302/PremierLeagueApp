package com.example.premierleagueapp.ui

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
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
fun PremierLeagueApp(navController: NavHostController = rememberNavController()) {
    var overview by rememberSaveable { mutableStateOf(false) }
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    fun sendMail() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:arthur.haus@student.hogent.be")
        intent.putExtra(Intent.EXTRA_EMAIL, "test")
        intent.putExtra(Intent.EXTRA_SUBJECT, "Hello World")
        if (intent.resolveActivity(context.packageManager) != null) {
            ContextCompat.startActivity(context, intent, null)
        } else {
            // Handle the case where no activity is available
            Toast.makeText(context, "No email client found", Toast.LENGTH_SHORT).show()
        }
    }
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
            )
        },
        floatingActionButton = {
            when (currentBackStackEntry?.destination?.route) {
                Destinations.Start.name -> {
                    FloatingActionButton(onClick = {
                        coroutineScope.launch { lazyListState.animateScrollToItem(0) }
                    }) {
                        Icon(Icons.Default.KeyboardArrowUp, contentDescription = stringResource(R.string.fab_scroll_up))
                    }
                }
                Destinations.Contact.name -> {
                    FloatingActionButton(onClick = {
                        sendMail()
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
                ContactScreen()
            }
            composable(Destinations.About.name) {
                AboutScreen()
            }
            composable(Destinations.Overview.name) {
                // OverviewContent()
            }
            composable(Destinations.Overview.name + "/{teamId}") { backStackEntry ->
                val teamId = backStackEntry.arguments?.getString("teamId")?.toIntOrNull()
                if (teamId != null) {
                    OverviewContent(teamId)
                } else {
                    Text(text = "Non existing team ID...")
                    Button(
                        onClick = { navController.navigate(Destinations.Start.name) },
                    ) {
                        Text(text = "Home")
                    }
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
