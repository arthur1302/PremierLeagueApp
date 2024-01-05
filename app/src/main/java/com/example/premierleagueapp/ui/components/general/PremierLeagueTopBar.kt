package com.example.premierleagueapp.ui.components.general

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource

/**
 * Composable that contains the [TopAppBar]
 *
 * @author Arthur Haus
 *
 * @param navigationIcon [Composable]
 * @param title [Int]
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PremierLeagueTopBar(navigationIcon: @Composable () -> Unit, title: Int) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(stringResource(title))
        },
        navigationIcon = navigationIcon,
        modifier = Modifier.testTag(stringResource(title)),
    )
}
