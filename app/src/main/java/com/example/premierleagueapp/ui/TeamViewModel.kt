package com.example.premierleagueapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.premierleagueapp.network.Team
import com.example.premierleagueapp.network.TeamApi.teamService
import com.example.premierleagueapp.network.TeamApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class TeamViewModel : ViewModel() {
    private val _teamUiState = MutableStateFlow(TeamUiState())
    val teamUiState = _teamUiState.asStateFlow()

    init {
        getApiTeams()
    }

    private fun getApiTeams() {
        viewModelScope.launch {
            try {
                val result: Response<TeamApiResponse> = teamService.getTeams("e2b1a771617b483bb629ab23272611a3")
                if (result.isSuccessful) {
                    val teams: List<Team>? = result.body()?.teams
                    teams?.let {
                        // Log de teamgegevens
                        for (team in it) {
                            Log.i("Team Name: ", team.name)
                            Log.i("Team Crest: ", team.crest)
                        }

                        // Update de staat van het UI
                        _teamUiState.value = TeamUiState(teams = it)
                    }
                } else {
                    Log.e("Error: ", "${result.code()} - ${result.message()}")
                }
            } catch (e: Exception) {
                Log.e("Errorf: ", e.message, e)
            }
        }
    }
}
