package com.example.premierleagueapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.premierleagueapp.model.Table

@Composable
fun Ranking(tables: List<Table>, lazyListState: LazyListState) {
    LazyColumn(state = lazyListState) {
        items(tables) { table ->
            ElevatedCard(
                modifier = Modifier
                    .padding(16.dp, 4.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                ) {
                    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                        Text(table.position.toString(), style = MaterialTheme.typography.bodyLarge)
                    }
                    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                        Text(table.team.name, style = MaterialTheme.typography.bodyLarge)
                    }
                    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                        Text(table.points.toString(), style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}
