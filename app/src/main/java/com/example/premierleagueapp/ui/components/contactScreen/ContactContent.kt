package com.example.premierleagueapp.ui.components.contactScreen

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
import com.example.premierleagueapp.ui.components.helpers.MailComponent

/**
 * Composable that contains the content for the contact page
 *
 * @author Arthur Haus
 */
@Composable
fun ContactContent() {
    Column(Modifier.padding(8.dp, 8.dp, 8.dp).testTag(stringResource(R.string.contact_content))) {
        Text(stringResource(R.string.contact_paragraph1), style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(25.dp))
        MailComponent()
    }
}
