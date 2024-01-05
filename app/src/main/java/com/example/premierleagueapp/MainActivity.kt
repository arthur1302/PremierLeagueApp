package com.example.premierleagueapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.premierleagueapp.ui.PremierLeagueApp
import com.example.premierleagueapp.ui.theme.PremierLeagueAppTheme

/**
 * Main Activity of the application
 *
 * @author Arthur Haus
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PremierLeagueAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    PremierLeagueApp()
                }
            }
        }
    }
}
