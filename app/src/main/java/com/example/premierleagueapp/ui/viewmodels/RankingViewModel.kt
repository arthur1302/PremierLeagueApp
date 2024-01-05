package com.example.premierleagueapp.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.premierleagueapp.SoccerApplication
import com.example.premierleagueapp.data.SoccerRepository
import com.example.premierleagueapp.model.Table
import com.example.premierleagueapp.ui.TableApiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RankingViewModel(private val soccerRepository: SoccerRepository) : ViewModel() {
    var tableApiState: TableApiState by mutableStateOf(TableApiState.Loading)
        private set

    lateinit var uiTableState: StateFlow<List<Table>>

    init {
        getRepoStandings()
    }

    private fun getRepoStandings() {
        try {
            viewModelScope.launch {
                uiTableState = soccerRepository.getStandings()
                    .stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(5_000L),
                        initialValue = listOf(),
                    )
                tableApiState = TableApiState.Success
            }
        } catch (e: Exception) {
            tableApiState = TableApiState.Error
            Log.e("Error: ", e.message, e)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SoccerApplication
                val soccerRepository = application.container.soccerRepository
                RankingViewModel(soccerRepository)
            }
        }
    }
}
