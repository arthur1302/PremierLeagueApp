package com.example.premierleagueapp.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.premierleagueapp.SoccerApplication
import com.example.premierleagueapp.data.SoccerRepository
import com.example.premierleagueapp.model.Coach
import com.example.premierleagueapp.model.Match
import com.example.premierleagueapp.model.Team
import com.example.premierleagueapp.ui.MatchApiState
import com.example.premierleagueapp.ui.TeamApiDetailState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TeamDetailsViewModel(private val soccerRepository: SoccerRepository) : ViewModel() {
    var teamApiDetailState: TeamApiDetailState by mutableStateOf(TeamApiDetailState.Loading)
        private set

    var matchApiState: MatchApiState by mutableStateOf(MatchApiState.Loading)
        private set

    lateinit var uiTeamState: StateFlow<Team?>
    lateinit var uiMatchListState: StateFlow<List<Match>>

    fun getSingleTeam(teamId: Int) {
        try {
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
        try {
            viewModelScope.launch {
                uiMatchListState = soccerRepository.getMatchesByTeam(teamId)
                    .stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(5_000L),
                        initialValue = listOf(),
                    )
                matchApiState = MatchApiState.Success
            }
        } catch (e: Exception) {
            matchApiState = MatchApiState.Error
            Log.e("Error: ", e.message, e)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SoccerApplication
                val soccerRepository = application.container.soccerRepository
                TeamDetailsViewModel(soccerRepository)
            }
        }
    }
}
