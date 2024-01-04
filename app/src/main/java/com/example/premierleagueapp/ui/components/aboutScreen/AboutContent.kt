package com.example.premierleagueapp.ui.components.aboutScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.premierleagueapp.R

@Composable
fun AboutContent() {
    Column(
        Modifier
            .padding(start = 8.dp, end = 8.dp)
            .testTag(stringResource(R.string.about_content)),
    ) {
        Text(stringResource(R.string.about_paragraph1), style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(25.dp))
        Text(stringResource(R.string.about_paragraph2), style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(25.dp))
        Text(stringResource(R.string.about_paragraph3), style = MaterialTheme.typography.bodyLarge)
    }
}
