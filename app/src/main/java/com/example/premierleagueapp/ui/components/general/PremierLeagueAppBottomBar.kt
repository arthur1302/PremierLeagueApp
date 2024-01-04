package com.example.premierleagueapp.ui.components.general

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import com.example.premierleagueapp.R
import kotlinx.coroutines.CoroutineScope

@Composable
fun PremierLeagueAppBottomBar(
    /*currentBackStackEntry: NavBackStackEntry?,
    coroutineScope: CoroutineScope,
    lazyListState: LazyListState,*/
    onHome: () -> Unit,
    onContact: () -> Unit,
    onAbout: () -> Unit,
    onCamera: () -> Unit,
) {
    BottomAppBar(
        containerColor = Color.White,
        contentColor = MaterialTheme.colorScheme.primary,
        //floatingActionButton = { PremierLeagueFab(currentBackStackEntry, coroutineScope, lazyListState) },
        actions = {
            IconButton(onClick = onHome) {
                Icon(Icons.Filled.Home, contentDescription = stringResource(R.string.home_button))
            }
            IconButton(onClick = onContact) {
                Icon(Icons.Filled.Call, contentDescription = stringResource(R.string.contact_button))
            }
            IconButton(onClick = onAbout) {
                Icon(Icons.Filled.Info, contentDescription = stringResource(R.string.about_button))
            }
            IconButton(onClick = onCamera) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        },
    )
}
