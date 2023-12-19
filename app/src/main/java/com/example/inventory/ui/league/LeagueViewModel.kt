package com.example.inventory.ui.league

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.LeagueRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.setu.model.League

class LeagueViewModel(
    savedStateHandle: SavedStateHandle,
    private val leaguesRepository: LeagueRepository): ViewModel() {

    //private val leaguesRepository = OfflineLeagueRepository()


    private val leagueId: String =  checkNotNull(savedStateHandle[LeagueDestination.leagueId])

    val leagueUiState: StateFlow<LeagueUiState> =
        leaguesRepository.getLeagueStream(leagueId.toInt())
            .filterNotNull()
            .map {
                LeagueUiState(it)
            }.stateIn(
                scope = viewModelScope,
                started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = LeagueUiState()
            )
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    fun deleteLeague(league: League) {
        viewModelScope.launch {
            leaguesRepository.deleteLeague(league)
            // navigate back to home screen

        }
    }
}


data class LeagueUiState(val league: League = League())