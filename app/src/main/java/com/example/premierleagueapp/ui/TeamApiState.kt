package com.example.premierleagueapp.ui

import com.example.premierleagueapp.network.Team

sealed interface TeamApiState {
    object Error : TeamApiState
    object Loading : TeamApiState
    data class Succes(val teams: List<Team>) : TeamApiState
}
