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
import com.example.premierleagueapp.model.Team
import com.example.premierleagueapp.ui.TeamApiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TeamsViewModel(private val soccerRepository: SoccerRepository) : ViewModel() {
    var teamApiState: TeamApiState by mutableStateOf(TeamApiState.Loading)
        private set

    lateinit var uiListState: StateFlow<List<Team>>

    init {
        getRepoTeams()
    }

    private fun getRepoTeams() {
        try {
            viewModelScope.launch {
                // soccerRepository.refresh()
                uiListState = soccerRepository.getTeams()
                    .stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(5_000L),
                        initialValue = listOf(),
                    )
                teamApiState = TeamApiState.Success
            }
        } catch (e: Exception) {
            teamApiState = TeamApiState.Error
            Log.e("Error: ", e.message, e)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SoccerApplication
                val soccerRepository = application.container.soccerRepository
                TeamsViewModel(soccerRepository)
            }
        }
    }
}
