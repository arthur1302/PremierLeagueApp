package com.example.premierleagueapp.network.conectivity

/**
 * Sealed class that contains the data objects for the connectivity status
 *
 * @author Arthur Haus
 */
sealed class ConnectionStatus {
    data object Available : ConnectionStatus()
    data object Unavailable : ConnectionStatus()
}
