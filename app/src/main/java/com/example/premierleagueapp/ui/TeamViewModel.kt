package com.example.premierleagueapp.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.premierleagueapp.data.TeamApiResponse
import com.example.premierleagueapp.network.Team
import com.example.premierleagueapp.network.TeamApi.teamService
import com.example.premierleagueapp.network.asDomainObjects
import kotlinx.coroutines.launch
import retrofit2.Response

class TeamViewModel : ViewModel() {

    var teamApiState: TeamApiState by mutableStateOf(TeamApiState.Loading)
        private set

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
                        val sortedTeams = it.sortedBy { team -> team.name }
                        teamApiState = TeamApiState.Succes(sortedTeams.asDomainObjects())
                    }
                } else {
                    Log.e("Error: ", "${result.code()} - ${result.message()}")
                }
            } catch (e: Exception) {
                Log.e("Error: ", e.message, e)
            }
        }
    }
}
