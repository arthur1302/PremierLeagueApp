package com.example.premierleagueapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun TeamCard(name: String = "", shortName: String = "", imageUrl: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = modifier
            .clickable { onClick() }
            .padding(16.dp, 4.dp)
            .testTag(name),
    ) {
        Row(modifier = modifier.fillMaxWidth().padding(8.dp)) {
            Image(
                painter = rememberImagePainter(
                    data = imageUrl,
                    builder = {
                        crossfade(true)
                    },
                ),
                contentDescription = null,
                modifier = modifier
                    .size(40.dp)
                    .background(shape = CircleShape, color = Color.White)
                    .align(Alignment.CenterVertically),
            )
            Column(modifier = modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                Text(name, style = MaterialTheme.typography.bodyLarge)
                Text(shortName, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
