@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.premierleagueapp.ui

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.premierleagueapp.fake.FakeApiSoccerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class SoccerViewModelTest {

    private lateinit var viewModel: SoccerViewModel

    @get:Rule
    val testDispatcher = TestDispatchersRule()

    @Before
    fun initializeViewModel() {
        viewModel = SoccerViewModel(FakeApiSoccerRepository())
    }

    @Test
    fun testGetApiTeams() {
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
