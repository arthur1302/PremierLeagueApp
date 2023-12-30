package com.example.premierleagueapp.ui

import com.example.premierleagueapp.network.Match
import com.example.premierleagueapp.network.Team

sealed interface TeamApiState {
    object Error : TeamApiState
    object Loading : TeamApiState
    data class Success(val teams: List<Team>) : TeamApiState
}

sealed interface TeamApiDetailState {
    object Error : TeamApiDetailState
    object Loading : TeamApiDetailState
    data class Success(val team: Team) : TeamApiDetailState
}

sealed interface MatchApiState {
    object Error : MatchApiState
    object Loading : MatchApiState
    data class Success(val matches: List<Match>) : MatchApiState
}