@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.premierleagueapp.ui

import androidx.lifecycle.Observer
import com.example.premierleagueapp.fake.FakeApiSoccerRepository
import com.example.premierleagueapp.fake.FakeDataSource
import com.example.premierleagueapp.model.Team
import com.example.premierleagueapp.ui.viewmodels.RankingViewModel
import com.example.premierleagueapp.ui.viewmodels.TeamDetailsViewModel
import com.example.premierleagueapp.ui.viewmodels.TeamsViewModel
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class SoccerViewModelTest {

    private lateinit var rankingViewModel: RankingViewModel
    private lateinit var teamsViewModel: TeamsViewModel
    private lateinit var teamDetailsViewModel: TeamDetailsViewModel

    @get:Rule
    val testDispatcher = TestDispatchersRule()

    @Before
    fun initializeViewModel() {
        rankingViewModel = RankingViewModel(FakeApiSoccerRepository())
        teamsViewModel = TeamsViewModel(FakeApiSoccerRepository())
        teamDetailsViewModel = TeamDetailsViewModel(FakeApiSoccerRepository())
    }

    @Test
    fun testGetApiTeams() = runTest {
        val observer = mockk<Observer<List<Team>>>(relaxed = true)
        val teams = FakeDataSource.getFakeTeamsResponse().body()!!.teams

        // Observeer de uiListState en voeg de observer toe
        teamsViewModel.uiListState.collect(observer)

        // Start de test dispatcher
        testDispatcher.runCurrent()

        // Assert dat de data correct is
        coVerify { observer.onChanged(teams) }
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
