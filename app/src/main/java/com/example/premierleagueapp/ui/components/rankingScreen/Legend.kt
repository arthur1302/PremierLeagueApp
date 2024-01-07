package com.example.premierleagueapp.ui.components.rankingScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.premierleagueapp.R

/**
 * Composable for the legend used in the ranking
 *
 * @author Arthur Haus
 */
@Composable
fun Legend() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 16.dp),
    ) {
        Row(Modifier.padding(bottom = 8.dp)) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(Color.Green)
                    .padding(8.dp),
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(stringResource(R.string.champions_league), style = MaterialTheme.typography.bodyLarge)
        }
        Row(Modifier.padding(bottom = 8.dp)) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(Color.Yellow)
                    .padding(8.dp),
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(stringResource(R.string.europa_league), style = MaterialTheme.typography.bodyLarge)
        }
        Row(Modifier.padding(bottom = 8.dp)) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(Color(0xFFFFA500))
                    .padding(8.dp),
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(stringResource(R.string.mid_table), style = MaterialTheme.typography.bodyLarge)
        }
        Row {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(Color.Red)
                    .padding(8.dp),
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(stringResource(R.string.degradation_zone), style = MaterialTheme.typography.bodyLarge)
        }
    }
}
