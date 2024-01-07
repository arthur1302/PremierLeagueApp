package com.example.premierleagueapp.network.conectivity

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext

/**
 * Composable that returns the connectivity status
 *
 * @author Arthur Haus
 * @return [ConnectionStatus]
 */
@Composable
fun connectivityStatus(): State<ConnectionStatus> {
    val mCtx = LocalContext.current
    return produceState(initialValue = mCtx.currentConnectivityStatus) {
        mCtx.observeConnectivityAsFlow().collect { value = it }
    }
}
