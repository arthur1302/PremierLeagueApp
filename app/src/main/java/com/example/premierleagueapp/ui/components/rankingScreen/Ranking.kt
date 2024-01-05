package com.example.premierleagueapp.ui.components.rankingScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.premierleagueapp.R
import com.example.premierleagueapp.model.Table
import com.example.premierleagueapp.ui.components.helpers.imageHandler

@Composable
fun Ranking(tables: List<Table>, lazyListState: LazyListState) {
    var painter: Painter = rememberImagePainter(R.drawable.england)
    LazyColumn(state = lazyListState) {
        items(tables) { table ->
            val cardColor = when (table.position) {
                in 1..4 -> Color.Green.copy(alpha = 0.4F)
                in 5..5 -> Color.Yellow.copy(alpha = 0.4F)
                in 6..17 -> Color(0xFFFFA500).copy(alpha = 0.4F)
                else -> Color.Red.copy(alpha = 0.4F)
            }

            Card(
                modifier = Modifier
                    .padding(16.dp, 4.dp)
                    .testTag(table.team.name),
                colors = CardDefaults.cardColors(
                    containerColor = cardColor,
                ),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp).weight(0.5F)) {
                        Text(table.position.toString(), style = MaterialTheme.typography.bodyLarge)
                    }
                    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp).weight(2F)) {
                        Row(horizontalArrangement = Arrangement.SpaceBetween) {
                            painter = imageHandler(table.team.crest, painter)
                            Image(
                                painter = painter,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(20.dp)
                                    .background(shape = CircleShape, color = Color.Transparent),
                            )
                            Text(text = table.team.shortName, modifier = Modifier.padding(start = 12.dp), style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp).weight(0.5F)) {
                        Text(table.points.toString(), style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
        item {
            Legend()
        }
    }
}
