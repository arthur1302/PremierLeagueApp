package com.example.premierleagueapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.premierleagueapp.R

@Composable
fun AboutScreen() {
    val context = LocalContext.current
    Column(
        Modifier
            .padding(start = 8.dp, end = 8.dp)
            .testTag(stringResource(R.string.about_content)),
    ) {
        Text(text = "Hello, I'm Arthur Haus, a final-year student in Applied Computer Science at HoGent.", style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(25.dp))
        Text(text = "This application is a project that I created for the Mobile Software Development course.", style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(25.dp))
        Text(text = "Thanks for using my Android application!", style = MaterialTheme.typography.bodyLarge)
    }
}
