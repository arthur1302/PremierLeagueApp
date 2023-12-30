package com.example.premierleagueapp.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.premierleagueapp.data.MatchApiResponse
import com.example.premierleagueapp.data.TeamApiResponse
import com.example.premierleagueapp.network.Match
import com.example.premierleagueapp.network.Team
import com.example.premierleagueapp.network.TeamApi.teamService
import com.example.premierleagueapp.network.asDomainObjects
import kotlinx.coroutines.launch
import retrofit2.Response

class TeamViewModel : ViewModel() {

    var teamApiState: TeamApiState by mutableStateOf(TeamApiState.Loading)
        private set

    var teamApiDetailState: TeamApiDetailState by mutableStateOf(TeamApiDetailState.Loading)
        private set

    var matchApiState: MatchApiState by mutableStateOf(MatchApiState.Loading)
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
                        teamApiState = TeamApiState.Success(sortedTeams.asDomainObjects())
                    }
                } else {
                    teamApiState = TeamApiState.Error
                    Log.e("Error: ", "${result.code()} - ${result.message()}")
                }
            } catch (e: Exception) {
                teamApiState = TeamApiState.Error
                Log.e("Error: ", e.message, e)
            }
        }
    }

    fun getSingleTeam(teamId: Int) {
        viewModelScope.launch {
            try {
                val result: Response<Team> = teamService.getSingleTeam(teamId, "e2b1a771617b483bb629ab23272611a3")
                if (result.isSuccessful) {
                    val team: Team? = result.body()
                    team?.let {
                        // Verwerk het enkele team zoals nodig
                        teamApiDetailState = TeamApiDetailState.Success(team)
                        Log.i("TEAMMMM", team.name)
                    }
                } else {
                    teamApiDetailState = TeamApiDetailState.Error
                    Log.e("Error: ", "${result.code()} - ${result.message()}")
                }
            } catch (e: Exception) {
                teamApiDetailState = TeamApiDetailState.Error
                Log.e("Error: ", e.message, e)
            }
        }
    }

    fun getMatchesByTeam(teamId: Int) {
        viewModelScope.launch {
            try {
                val result: Response<MatchApiResponse> = teamService.getMatcesByTeam(teamId, "e2b1a771617b483bb629ab23272611a3")
                if (result.isSuccessful) {
                    val matches: List<Match>? = result.body()?.matches
                    matches?.let {
                        matchApiState = MatchApiState.Success(matches.asDomainObjects())
                    }
                } else {
                    matchApiState = MatchApiState.Error
                    Log.e("Error: ", "${result.code()} - ${result.message()}")
                }
            } catch (e: Exception) {
                matchApiState = MatchApiState.Error
                Log.e("Error: ", e.message, e)
            }
        }
    }
}
