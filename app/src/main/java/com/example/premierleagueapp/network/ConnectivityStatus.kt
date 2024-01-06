package com.example.premierleagueapp.network

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext

@Composable
fun connectivityStatus(): State<ConnectionStatus> {
    val mCtx = LocalContext.current
    return produceState(initialValue = mCtx.currentConnectivityStatus) {
        mCtx.observeConnectivityAsFlow().collect { value = it }
    }
}
