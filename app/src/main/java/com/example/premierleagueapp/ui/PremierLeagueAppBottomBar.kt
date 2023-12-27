package com.example.premierleagueapp.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun PremierLeagueAppBottomBar(onHome: () -> Unit, onContact: () -> Unit, onAbout: () -> Unit) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
        actions = {
            IconButton(onClick = onHome) {
                Icon(Icons.Filled.Home, contentDescription = "Home button")
            }
            IconButton(onClick = onContact) {
                Icon(Icons.Filled.Call, contentDescription = "Contact button")
            }
            IconButton(onClick = onAbout) {
                Icon(Icons.Filled.Info, contentDescription = "About button")
            }
        },
    )
}
