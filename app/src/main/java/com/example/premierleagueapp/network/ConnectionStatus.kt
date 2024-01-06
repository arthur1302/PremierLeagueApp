package com.example.premierleagueapp.network

sealed class ConnectionStatus {
    object Available : ConnectionStatus()
    object Unavailable : ConnectionStatus()
}
