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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TeamCard(name: String = "", country: String = "", imageResourceId: Int, onClick: () -> Unit = {}, modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = modifier
            .clickable { onClick() }
            .padding(16.dp, 4.dp),
        /*colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ),*/
    ) {
        Row(modifier = modifier.fillMaxWidth().padding(8.dp)) {
            Image(
                painter = painterResource(id = imageResourceId),
                contentDescription = null,
                modifier = modifier
                    .size(40.dp)
                    .background(shape = CircleShape, color = Color.White)
                    .align(Alignment.CenterVertically),
            )
            Column(modifier = modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                Text(name, fontSize = 16.sp)
                Text(country, fontSize = 12.sp)
            }
        }
    }
}
