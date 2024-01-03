package com.example.premierleagueapp.ui

sealed interface TeamApiState {
    data object Error : TeamApiState
    data object Loading : TeamApiState
    data object Success : TeamApiState
}

sealed interface TeamApiDetailState {
    data object Error : TeamApiDetailState
    data object Loading : TeamApiDetailState
    data object Success : TeamApiDetailState
}

sealed interface MatchApiState {
    data object Error : MatchApiState
    data object Loading : MatchApiState
    data object Success : MatchApiState
}
