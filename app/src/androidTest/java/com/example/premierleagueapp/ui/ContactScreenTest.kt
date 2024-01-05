package com.example.premierleagueapp.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.premierleagueapp.R
import com.example.premierleagueapp.assertCurrentRouteName
import org.junit.Before
import org.junit.Rule
import org.junit.Test
/**
 * Test class for the contact page
 *
 * @author Arthur Haus
 */
class ContactScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupContactScreen() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            PremierLeagueApp(navController = navController)
            // navController.navigate(Destinations.Contact.name)
        }
    }

    @Test
    fun contactScreen_content() {
        goToContactScreen()
        val content = composeTestRule.activity.getString(R.string.contact_content)
        composeTestRule.onNodeWithTag(content)
    }

    @Test
    fun contactScreen_title() {
        goToContactScreen()
        val title = composeTestRule.activity.getString(R.string.contact_title)
        composeTestRule.onNodeWithTag(title).assertExists()
    }

    @Test
    fun contactScreen_fab() {
        goToContactScreen()
        val fab1 = composeTestRule.activity.getString(R.string.fab_scroll_up)
        composeTestRule.onNodeWithContentDescription(fab1).assertDoesNotExist()
        val fab2 = composeTestRule.activity.getString(R.string.fab_send_email)
        composeTestRule.onNodeWithContentDescription(fab2).assertExists()
    }

    @Test
    fun contactScreen_navigationButtons() {
        goToContactScreen()
        val homeButton = composeTestRule.activity.getString(R.string.home_button)
        val contactButton = composeTestRule.activity.getString(R.string.contact_button)
        val aboutButton = composeTestRule.activity.getString(R.string.about_button)
        val rankingButton = composeTestRule.activity.getString(R.string.ranking_button)

        composeTestRule.onNodeWithContentDescription(homeButton).assertExists()
        composeTestRule.onNodeWithContentDescription(contactButton).assertExists()
        composeTestRule.onNodeWithContentDescription(aboutButton).assertExists()
        composeTestRule.onNodeWithContentDescription(rankingButton).assertExists()
    }

    @Test
    fun contactScreen_navigateBack() {
        goToContactScreen()
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).performClick()
        navController.assertCurrentRouteName(Destinations.Start.name)
    }

    private fun goToContactScreen() {
        val contactButton = composeTestRule.activity.getString(R.string.contact_button)
        composeTestRule.onNodeWithContentDescription(contactButton).performClick()
    }
}
