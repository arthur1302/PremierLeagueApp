package com.example.premierleagueapp.ui.components.general

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.navigation.NavBackStackEntry
import com.example.premierleagueapp.R
import com.example.premierleagueapp.ui.Destinations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Composable that contains the [FloatingActionButton]
 *
 * @author Arthur Haus
 *
 * @param currentBackStackEntry [NavBackStackEntry]
 * @param coroutineScope [CoroutineScope]
 * @param lazyListState [LazyListState]
 */
@Composable
fun PremierLeagueFab(
    currentBackStackEntry: NavBackStackEntry?,
    coroutineScope: CoroutineScope,
    lazyListState: LazyListState,
) {
    val context = LocalContext.current

    /**
     * Function to send an email and check whether there is an email client installed or not
     *
     * @author Arthur Haus
     */
    fun sendMail() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:arthur.haus@student.hogent.be")
        intent.putExtra(Intent.EXTRA_EMAIL, "test")
        intent.putExtra(Intent.EXTRA_SUBJECT, "Hello World")
        if (intent.resolveActivity(context.packageManager) != null) {
            ContextCompat.startActivity(context, intent, null)
        } else {
            Toast.makeText(context, "No email client found", Toast.LENGTH_SHORT).show()
        }
    }

    // Depending which route is active, there will be shown an other FAB
    when (currentBackStackEntry?.destination?.route) {
        Destinations.Start.name -> {
            FloatingActionButton(onClick = {
                coroutineScope.launch { lazyListState.animateScrollToItem(0) }
            }, containerColor = MaterialTheme.colorScheme.primary) {
                Icon(
                    Icons.Default.KeyboardArrowUp,
                    contentDescription = stringResource(R.string.fab_scroll_up),
                )
            }
        }

        Destinations.Ranking.name -> {
            FloatingActionButton(onClick = {
                coroutineScope.launch { lazyListState.animateScrollToItem(0) }
            }, containerColor = MaterialTheme.colorScheme.primary) {
                Icon(
                    Icons.Default.KeyboardArrowUp,
                    contentDescription = stringResource(R.string.fab_scroll_up),
                )
            }
        }

        Destinations.Contact.name -> {
            FloatingActionButton(onClick = {
                sendMail()
            }, containerColor = MaterialTheme.colorScheme.primary) {
                Icon(
                    Icons.Default.Email,
                    contentDescription = stringResource(R.string.fab_send_email),
                )
            }
        }
    }
}
