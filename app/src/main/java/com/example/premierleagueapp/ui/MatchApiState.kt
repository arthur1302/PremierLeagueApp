package com.example.premierleagueapp.ui

import com.example.premierleagueapp.network.Match

sealed interface MatchApiState {
    object Error : MatchApiState
    object Loading : MatchApiState
    data class Success(val matches: List<Match>) : MatchApiState
}
