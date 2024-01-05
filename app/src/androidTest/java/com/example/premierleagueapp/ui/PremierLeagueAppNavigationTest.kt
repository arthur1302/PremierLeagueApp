package com.example.premierleagueapp.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.premierleagueapp.R
import com.example.premierleagueapp.assertCurrentRouteName
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PremierLeagueAppNavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            PremierLeagueApp(navController = navController)
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Destinations.Start.name)
    }

    @Test
    fun navHost_verifyBackButtonOnHomeScreen() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    @Test
    fun navHost_verifyNavigateToAbout() {
        navigateToAbout()
        navController.assertCurrentRouteName(Destinations.About.name)
    }

    @Test
    fun navHost_verifyBackButtonOnAboutPage() {
        navigateToAbout()
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertExists()
    }

    @Test
    fun navHost_verifyNavigateToContact() {
        navigateToContact()
        navController.assertCurrentRouteName(Destinations.Contact.name)
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertExists()
    }

    @Test
    fun navHost_verifyBackButtonOnContactPage() {
        navigateToContact()
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertExists()
    }

    @Test
    fun navHost_verifyNavigateToHomeWithHomeButton() {
        navigateToContact()
        navigateToAbout()
        navigateHome()
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    @Test
    fun navHost_verifyBackButtonOnAboutScreen() {
        navigateToAbout()
        performNavigateUp()
        navController.assertCurrentRouteName(Destinations.Start.name)
    }

    @Test
    fun navHost_verifyBackButtonOnContactScreen() {
        navigateToContact()
        performNavigateUp()
        navController.assertCurrentRouteName(Destinations.Start.name)
    }

    @Test
    fun navHost_verifyNavigateToRanking() {
        navigateToRanking()
        navController.assertCurrentRouteName(Destinations.Ranking.name)
    }

    @Test
    fun navHost_verifyBackButtonOnRankingPage() {
        navigateToRanking()
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertExists()
    }

    private fun performNavigateUp() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).performClick()
    }

    private fun navigateToAbout() {
        val about = composeTestRule.activity.getString(R.string.about_button)
        composeTestRule.onNodeWithContentDescription(about)
            .performClick()
    }

    private fun navigateToRanking() {
        val ranking = composeTestRule.activity.getString(R.string.ranking_button)
        composeTestRule.onNodeWithContentDescription(ranking)
            .performClick()
    }

    private fun navigateToContact() {
        val contact = composeTestRule.activity.getString(R.string.contact_button)
        composeTestRule.onNodeWithContentDescription(contact)
            .performClick()
    }

    private fun navigateHome() {
        val home = composeTestRule.activity.getString(R.string.home_button)
        composeTestRule.onNodeWithContentDescription(home)
            .performClick()
    }
}
