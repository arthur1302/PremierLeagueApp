package com.example.premierleagueapp.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.example.premierleagueapp.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PremierLeagueAppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun initializeApp() {
        composeTestRule.setContent {
            PremierLeagueApp()
        }
    }

    @Test
    fun showsHomeButton() {
        composeTestRule
            .onNodeWithContentDescription(composeTestRule.activity.getString(R.string.home_button))
            .assertIsDisplayed()
            .assertIsEnabled()
    }

    @Test
    fun showsAboutButton() {
        composeTestRule
            .onNodeWithContentDescription(composeTestRule.activity.getString(R.string.about_button))
            .assertIsDisplayed()
            .assertIsEnabled()
    }

    @Test
    fun showsContactButton() {
        composeTestRule
            .onNodeWithContentDescription(composeTestRule.activity.getString(R.string.contact_button))
            .assertIsDisplayed()
            .assertIsEnabled()
    }

    @Test
    fun showsRankingButton() {
        composeTestRule
            .onNodeWithContentDescription(composeTestRule.activity.getString(R.string.ranking_button))
            .assertIsDisplayed()
            .assertIsEnabled()
    }
}
