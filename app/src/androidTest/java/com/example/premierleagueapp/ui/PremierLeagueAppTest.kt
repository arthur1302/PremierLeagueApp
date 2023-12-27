package com.example.premierleagueapp.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PremierLeagueAppTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun initializeApp() {
        composeTestRule.setContent {
            PremierLeagueApp()
        }
    }

    @Test
    fun showsHomeButton() {
        composeTestRule
            .onNodeWithContentDescription("Home button")
            .assertIsDisplayed()
            .assertIsEnabled()
    }
}
