package com.example.premierleagueapp.ui
import androidx.activity.ComponentActivity
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.premierleagueapp.R
import com.example.premierleagueapp.model.Coach
import com.example.premierleagueapp.model.Player
import com.example.premierleagueapp.model.Team
import com.example.premierleagueapp.ui.components.startScreen.TeamsList
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Test class for the home page
 *
 * @author Arthur Haus
 */
class HomeScreenTest {

    private val team1 = Team(0, "Test", "T", "", "www.test.com", "TE", Coach("Coach", "EN"), "", listOf(Player("Name", "Attacker", "England"), Player("Name", "Attacker", "England")))
    private val team2 = Team(1, "Test2", "T2", "", "www.test.com", "TE2", Coach("Coach", "EN"), "", listOf(Player("Name", "Attacker", "England"), Player("Name", "Attacker", "England")))

    private val teams = listOf(team1, team2)

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
        composeTestRule.onNodeWithTag(title).assertExists()
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
        val rankingButton = composeTestRule.activity.getString(R.string.ranking_button)

        composeTestRule.onNodeWithContentDescription(homeButton).assertExists()
        composeTestRule.onNodeWithContentDescription(contactButton).assertExists()
        composeTestRule.onNodeWithContentDescription(aboutButton).assertExists()
        composeTestRule.onNodeWithContentDescription(rankingButton).assertExists()
    }

    @Test
    fun homeScreen_navigateToDetailPage() {
        composeTestRule.onNodeWithTag("Test").assertExists()
        // .performClick()
        // navController.assertCurrentRouteName(Destinations.Overview.name)
    }

    @Test
    fun homeScreen_navigateToAboutPage() {
        val aboutButton = composeTestRule.activity.getString(R.string.about_button)
        composeTestRule.onNodeWithContentDescription(aboutButton).performClick()
        val title = composeTestRule.activity.getString(R.string.about_title)
        composeTestRule.onNodeWithTag(title).assertExists()
    }

    @Test
    fun homeScreen_navigateToContactPage() {
        val aboutButton = composeTestRule.activity.getString(R.string.contact_button)
        composeTestRule.onNodeWithContentDescription(aboutButton).performClick()
        val title = composeTestRule.activity.getString(R.string.contact_title)
        composeTestRule.onNodeWithTag(title).assertExists()
    }

    @Test
    fun homeScreen_navigateToRankingPage() {
        val aboutButton = composeTestRule.activity.getString(R.string.ranking_button)
        composeTestRule.onNodeWithContentDescription(aboutButton).performClick()
        val title = composeTestRule.activity.getString(R.string.ranking_title)
        composeTestRule.onNodeWithTag(title).assertExists()
    }
}
