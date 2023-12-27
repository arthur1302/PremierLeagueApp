package com.example.premierleagueapp.ui

import androidx.lifecycle.ViewModel
import com.example.premierleagueapp.data.Team
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TeamViewModel : ViewModel() {
    private val _teamUiState = MutableStateFlow(TeamUiState(teams = Team.getAll()))
    val teamUiState = _teamUiState.asStateFlow()
}
