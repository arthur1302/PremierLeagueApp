@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.premierleagueapp.ui

import com.example.premierleagueapp.fake.FakeApiSoccerRepository
import com.example.premierleagueapp.fake.FakeDataSource
import com.example.premierleagueapp.ui.viewmodels.TeamsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * ViewModel testing
 *
 * @author Arthur Haus
 */
class SoccerViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatchersRule()

    @Test
    fun testGetApiTeams() = runTest {
        val teamsViewModel = TeamsViewModel(FakeApiSoccerRepository())
        val fakeApiSoccerRepository = FakeApiSoccerRepository()
        val teams = FakeDataSource.getFakeTeamsResponse().body()!!.teams
        for (team in teams) {
            fakeApiSoccerRepository.insert(team)
        }

        Assert.assertEquals(teamsViewModel.uiListState.value, teams)
    }
}

class TestDispatchersRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}
