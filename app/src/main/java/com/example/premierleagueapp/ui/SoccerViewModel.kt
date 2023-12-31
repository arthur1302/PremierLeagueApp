package com.example.premierleagueapp.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.premierleagueapp.SoccerApplication
import com.example.premierleagueapp.data.SoccerRepository
import com.example.premierleagueapp.model.Coach
import com.example.premierleagueapp.network.Team
import com.example.premierleagueapp.network.asDomainObjects
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SoccerViewModel(
    private val soccerRepository: SoccerRepository,
) : ViewModel() {

    var teamApiState: TeamApiState by mutableStateOf(TeamApiState.Loading)
        private set

    var teamApiDetailState: TeamApiDetailState by mutableStateOf(TeamApiDetailState.Loading)
        private set

    var matchApiState: MatchApiState by mutableStateOf(MatchApiState.Loading)
        private set

    lateinit var uiListState: StateFlow<List<Team>>

    lateinit var uiTeamState: StateFlow<Team?>
    init {
        getRepoTeams()
    }

    private fun getRepoTeams() {
        try {
            viewModelScope.launch { soccerRepository.refresh() }
            uiListState = soccerRepository.getTeams()
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = listOf(),
                )
            teamApiState = TeamApiState.Success
        } catch (e: Exception) {
            teamApiState = TeamApiState.Error
            Log.e("Error: ", e.message, e)
        }
    }

    fun getSingleTeam(teamId: Int) {
        Log.i("TESTBEFORE", "Voor try")
        try {
            Log.i("AFTERRR", "na try")
            uiTeamState = soccerRepository.getSingleTeam(teamId)
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = Team(0, "", "", "", "", "", Coach("", ""), "", listOf()),
                )
            teamApiDetailState = TeamApiDetailState.Success
        } catch (e: Exception) {
            teamApiDetailState = TeamApiDetailState.Error
            Log.e("Error: ", e.message, e)
        }
    }

    fun getMatchesByTeam(teamId: Int) {
        viewModelScope.launch {
            try {
                val matches = soccerRepository.getMatchesByTeam(teamId, "e2b1a771617b483bb629ab23272611a3")
                matches?.let {
                    matchApiState = MatchApiState.Success(matches.asDomainObjects())
                }
            } catch (e: Exception) {
                matchApiState = MatchApiState.Error
                Log.e("Error: ", e.message, e)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as SoccerApplication
                val soccerRepository = application.container.soccerRepository
                SoccerViewModel(soccerRepository)
            }
        }
    }
}
