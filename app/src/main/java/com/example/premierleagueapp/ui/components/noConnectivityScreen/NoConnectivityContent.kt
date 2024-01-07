package com.example.premierleagueapp.ui.components.noConnectivityScreen

import android.content.Intent
import android.provider.Settings.ACTION_WIFI_SETTINGS
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.premierleagueapp.R

/**
 * Composable that contains the content for the page that will be shown if there is no connection available
 *
 * @author Arthur Haus
 */
@Composable
fun NoConnectivityContent() {
    val painter: Painter = rememberImagePainter(R.drawable.offline)
    val context = LocalContext.current
    Column(
        modifier = Modifier.padding(8.dp, 8.dp, 8.dp).testTag(stringResource(R.string.no_connection_content)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(painter, null)
        Text(stringResource(R.string.offline_paragraph1))
        Text(stringResource(R.string.offline_paragraph2))
        Button(onClick = {
            val i = Intent(ACTION_WIFI_SETTINGS)
            context.startActivity(i)
        }, modifier = Modifier.padding(8.dp)) {
            Text(text = "Wifi Settings")
        }
    }
}
