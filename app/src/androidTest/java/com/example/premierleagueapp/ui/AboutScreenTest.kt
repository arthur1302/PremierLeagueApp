package com.example.premierleagueapp.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.premierleagueapp.R
import com.example.premierleagueapp.assertCurrentRouteName
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AboutScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupAboutScreen() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            PremierLeagueApp(navController = navController)
            // navController.navigate(Destinations.About.name)
        }
    }

    @Test
    fun aboutScreen_content() {
        goToAboutScreen()
        val content = composeTestRule.activity.getString(R.string.about_content)
        composeTestRule.onNodeWithTag(content)
    }

    @Test
    fun aboutScreen_title() {
        goToAboutScreen()
        val title = composeTestRule.activity.getString(R.string.about_title)
        composeTestRule.onNodeWithText(title).assertExists()
    }

    @Test
    fun aboutScreen_fab() {
        goToAboutScreen()
        val fab1 = composeTestRule.activity.getString(R.string.fab_scroll_up)
        composeTestRule.onNodeWithContentDescription(fab1).assertDoesNotExist()
        val fab2 = composeTestRule.activity.getString(R.string.fab_send_email)
        composeTestRule.onNodeWithContentDescription(fab2).assertDoesNotExist()
    }

    @Test
    fun aboutScreen_navigationButtons() {
        goToAboutScreen()
        val homeButton = composeTestRule.activity.getString(R.string.home_button)
        val contactButton = composeTestRule.activity.getString(R.string.contact_button)
        val aboutButton = composeTestRule.activity.getString(R.string.about_button)

        composeTestRule.onNodeWithContentDescription(homeButton).assertExists()
        composeTestRule.onNodeWithContentDescription(contactButton).assertExists()
        composeTestRule.onNodeWithContentDescription(aboutButton).assertExists()
    }

    @Test
    fun aboutScreen_navigateBack() {
        goToAboutScreen()
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).performClick()
        navController.assertCurrentRouteName(Destinations.Start.name)
    }

    private fun goToAboutScreen() {
        val aboutButton = composeTestRule.activity.getString(R.string.about_button)
        composeTestRule.onNodeWithContentDescription(aboutButton).performClick()
    }
}
