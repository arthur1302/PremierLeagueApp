package com.example.premierleagueapp.ui.components.general

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import com.example.premierleagueapp.R
import com.example.premierleagueapp.ui.Destinations
import kotlinx.coroutines.CoroutineScope

@Composable
fun PremierLeagueAppBottomBar(
    currentBackStackEntry: NavBackStackEntry?,
    coroutineScope: CoroutineScope,
    lazyListState: LazyListState,
    onHome: () -> Unit,
    onContact: () -> Unit,
    onAbout: () -> Unit,
    onRanking: () -> Unit,
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.onError,
        contentColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxWidth(),
        content = {
            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                NavBarItem(if (currentBackStackEntry?.destination?.route == Destinations.Start.name) { Icons.Filled.Home } else { Icons.Outlined.Home }, onHome, R.string.home_button)
                NavBarItem(if (currentBackStackEntry?.destination?.route == Destinations.Ranking.name) { Icons.Filled.List } else { Icons.Outlined.List }, onRanking, R.string.ranking_button)
                PremierLeagueFab(currentBackStackEntry, coroutineScope, lazyListState)
                NavBarItem(if (currentBackStackEntry?.destination?.route == Destinations.Contact.name) { Icons.Filled.Call } else { Icons.Outlined.Call }, onContact, R.string.contact_button)
                NavBarItem(if (currentBackStackEntry?.destination?.route == Destinations.About.name) { Icons.Filled.Info } else { Icons.Outlined.Info }, onAbout, R.string.about_button)
            }
        },
    )
}

@Composable
private fun NavBarItem(icon: ImageVector, onClick: () -> Unit, resource: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        IconButton(onClick = onClick, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Icon(
                icon,
                contentDescription = stringResource(resource),
            )
        }
        Text(stringResource(resource).split(" ")[0], style = MaterialTheme.typography.labelMedium)
    }
}
