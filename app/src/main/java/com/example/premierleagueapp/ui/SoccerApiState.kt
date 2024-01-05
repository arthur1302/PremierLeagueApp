package com.example.premierleagueapp.ui

/**
 * Interface for the TeamApiState
 */
sealed interface TeamApiState {
    data object Error : TeamApiState
    data object Loading : TeamApiState
    data object Success : TeamApiState
}

/**
 * Interface for the TeamApiDetailState
 */
sealed interface TeamApiDetailState {
    data object Error : TeamApiDetailState
    data object Loading : TeamApiDetailState
    data object Success : TeamApiDetailState
}

/**
 * Interface for the MatchApiState
 */
sealed interface MatchApiState {
    data object Error : MatchApiState
    data object Loading : MatchApiState
    data object Success : MatchApiState
}

/**
 * Interface for the TableApiState
 */
sealed interface TableApiState {
    data object Error : TableApiState
    data object Loading : TableApiState
    data object Success : TableApiState
}
