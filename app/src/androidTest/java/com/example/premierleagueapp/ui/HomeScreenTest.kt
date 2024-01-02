package com.example.premierleagueapp.ui
import androidx.activity.ComponentActivity
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.premierleagueapp.R
import com.example.premierleagueapp.model.Coach
import com.example.premierleagueapp.model.Player
import com.example.premierleagueapp.network.Team
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    val team1 = Team(0, "Test", "T", "", "www.test.com", "TE", Coach("Coach", "EN"), "", listOf(Player("Name", "Attacker", "England"), Player("Name", "Attacker", "England")))
    val team2 = Team(1, "Test2", "T2", "", "www.test.com", "TE2", Coach("Coach", "EN"), "", listOf(Player("Name", "Attacker", "England"), Player("Name", "Attacker", "England")))

    val teams = listOf(team1, team2)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupHomeScreen() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            TeamsList(teams = teams, onTeamClick = { _ -> }, lazyListState = rememberLazyListState())

            PremierLeagueApp(navController = navController)
        }
    }

    @Test
    fun homeScreen_listContent() {
        teams.forEach {
                team ->
            composeTestRule.onNodeWithTag(team.name).assertIsDisplayed()
        }
    }

    @Test
    fun homeScreen_title() {
        val title = composeTestRule.activity.getString(R.string.app_title)
        composeTestRule.onNodeWithText(title).assertExists()
    }

    @Test
    fun homeScreen_fab() {
        val fabText = composeTestRule.activity.getString(R.string.fab_scroll_up)
        composeTestRule.onNodeWithContentDescription(fabText).assertExists()
    }

    @Test
    fun homeScreen_navigationButtons() {
        val homeButton = composeTestRule.activity.getString(R.string.home_button)
        val contactButton = composeTestRule.activity.getString(R.string.contact_button)
        val aboutButton = composeTestRule.activity.getString(R.string.about_button)

        composeTestRule.onNodeWithContentDescription(homeButton).assertExists()
        composeTestRule.onNodeWithContentDescription(contactButton).assertExists()
        composeTestRule.onNodeWithContentDescription(aboutButton).assertExists()
    }

    @Test
    fun homeScreen_navigateToDetailPage() {
        composeTestRule.onNodeWithTag("Test").assertExists()
        // .performClick()
        // HIER NOG AAN WERKEN
        // TEST MAKEN VOOR HET NAVIGEREN? EVENTUEEL DE OVERVEIWPAGE MOCKEN ALS DEZE IS OPGESPLITST?
        // navController.assertCurrentRouteName(Destinations.Overview.name)
    }
}
