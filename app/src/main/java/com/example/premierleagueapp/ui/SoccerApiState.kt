package com.example.premierleagueapp.ui

sealed interface TeamApiState {
    object Error : TeamApiState
    object Loading : TeamApiState
    object Success : TeamApiState
}

sealed interface TeamApiDetailState {
    object Error : TeamApiDetailState
    object Loading : TeamApiDetailState
    object Success : TeamApiDetailState
}

sealed interface MatchApiState {
    object Error : MatchApiState
    object Loading : MatchApiState
    object Success : MatchApiState
}
