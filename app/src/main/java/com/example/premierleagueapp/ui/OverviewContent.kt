package com.example.premierleagueapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.premierleagueapp.ui.theme.PremierLeagueAppTheme

@Composable
fun OverviewContent(onBackPressed: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Overview Content")
        Button(onClick = { onBackPressed() }) {
            Text("Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OverviewPrev() {
    PremierLeagueAppTheme {
        OverviewContent(onBackPressed = {})
    }
}
