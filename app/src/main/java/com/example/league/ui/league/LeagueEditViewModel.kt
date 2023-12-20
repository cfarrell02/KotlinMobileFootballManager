package com.example.league.ui.league

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.league.data.LeagueRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.setu.model.League

class LeagueEditViewModel (
    savedStateHandle: SavedStateHandle,
    private val leaguesRepository: LeagueRepository
): ViewModel() {

    var leagueUiState by mutableStateOf(LeagueUiState())
        private set

    private val itemId: String = checkNotNull(savedStateHandle[LeagueDestination.leagueId])
    init {
        viewModelScope.launch {
            leagueUiState = leaguesRepository.getLeagueStream(itemId.toInt())
                .filterNotNull()
                .first()
                 .let { LeagueUiState(it) }
        }
    }


    private fun validateInput(uiState: LeagueUiState = leagueUiState): Boolean {
        return with(uiState) {
            league.name.isNotBlank() &&
                    league.country.isNotBlank() &&
                    league.type.isNotBlank()
        }
    }

    fun updateLeagueUiState(league: League) {
        this.leagueUiState = leagueUiState.copy(league = league)
    }

    suspend fun updateLeague() {
        if (validateInput()) {
            leaguesRepository.updateLeague(leagueUiState.league)
        }
    }


}