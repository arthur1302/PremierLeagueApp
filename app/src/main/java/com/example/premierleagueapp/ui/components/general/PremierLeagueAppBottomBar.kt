package com.example.premierleagueapp.ui.components.general

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    onCamera: () -> Unit,
) {
    BottomAppBar(
        containerColor = Color.White,
        contentColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxWidth(),
        content = {
            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                NavBarItem(if (currentBackStackEntry?.destination?.route == Destinations.Start.name) { Icons.Filled.Home } else { Icons.Outlined.Home }, onHome, R.string.home_button)
                NavBarItem(if (currentBackStackEntry?.destination?.route == Destinations.Contact.name) { Icons.Filled.Call } else { Icons.Outlined.Call }, onContact, R.string.contact_button)
                PremierLeagueFab(currentBackStackEntry, coroutineScope, lazyListState)
                NavBarItem(if (currentBackStackEntry?.destination?.route == Destinations.About.name) { Icons.Filled.Info } else { Icons.Outlined.Info }, onAbout, R.string.about_button)
                NavBarItem(if (currentBackStackEntry?.destination?.route == Destinations.Camera.name) { Icons.Filled.Add } else { Icons.Outlined.Add }, onCamera, R.string.add_button)
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
        Text(stringResource(resource).split(" ").get(0), style = MaterialTheme.typography.labelMedium)
    }
}
