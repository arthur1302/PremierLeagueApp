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
import com.example.premierleagueapp.assertCurrentRouteName
import com.example.premierleagueapp.model.Coach
import com.example.premierleagueapp.model.Player
import com.example.premierleagueapp.model.Table
import com.example.premierleagueapp.model.Team
import com.example.premierleagueapp.ui.components.rankingScreen.Ranking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Test class for the ranking page
 *
 * @author Arthur Haus
 */
class RankingScreenTest {

    private val team1 = Team(
        0, "Test", "T", "", "www.test.com", "TE", Coach("Coach", "EN"), "",
        listOf(
            Player("Name", "Attacker", "England"),
            Player("Name", "Attacker", "England"),
        ),
    )
    private val team2 = Team(
        1, "Test2", "T2", "", "www.test.com", "TE2", Coach("Coach", "EN"), "",
        listOf(
            Player("Name", "Attacker", "England"),
            Player("Name", "Attacker", "England"),
        ),
    )

    private val table1 = Table(
        1,
        team1,
        50,
    )

    private val table2 = Table(
        2,
        team2,
        45,
    )

    private val tables = listOf(table1, table2)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupRankingScreen() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            Ranking(tables, lazyListState = rememberLazyListState())
            PremierLeagueApp(navController = navController)
        }
    }

    @Test
    fun rankingScreen_listContent() {
        goToRankingScreen()
        tables.forEach {
                table ->
            composeTestRule.onNodeWithTag(table.team.name).assertIsDisplayed()
        }
    }

    @Test
    fun rankingScreen_title() {
        goToRankingScreen()
        val title = composeTestRule.activity.getString(R.string.ranking_title)
        composeTestRule.onNodeWithTag(title).assertExists()
    }

    @Test
    fun rankingScreen_fab() {
        goToRankingScreen()
        val fabText = composeTestRule.activity.getString(R.string.fab_scroll_up)
        composeTestRule.onNodeWithContentDescription(fabText).assertExists()
    }

    @Test
    fun rankingScreen_navigationButtons() {
        goToRankingScreen()
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
    fun rankingScreen_navigateBack() {
        goToRankingScreen()
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).performClick()
        navController.assertCurrentRouteName(Destinations.Start.name)
    }

    private fun goToRankingScreen() {
        val rankingButton = composeTestRule.activity.getString(R.string.ranking_button)
        composeTestRule.onNodeWithContentDescription(rankingButton).performClick()
    }
}
