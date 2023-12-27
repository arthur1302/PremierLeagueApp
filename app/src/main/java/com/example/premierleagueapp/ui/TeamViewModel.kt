package com.example.premierleagueapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.premierleagueapp.data.Team
import com.example.premierleagueapp.network.TeamApi.teamService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TeamViewModel : ViewModel() {
    private val _teamUiState = MutableStateFlow(TeamUiState(teams = Team.getAll()))
    val teamUiState = _teamUiState.asStateFlow()

    init {
        getApiTeams()
    }

    private fun getApiTeams() {
        viewModelScope.launch {
            val result = teamService.getTeams()
            println("The teams: $result")
        }
    }
}
