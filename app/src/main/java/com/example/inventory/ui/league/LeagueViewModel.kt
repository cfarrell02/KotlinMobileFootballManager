package com.example.inventory.ui.league

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.setu.model.Club

class LeagueViewModel: ViewModel() {

    //private val leaguesRepository = OfflineLeagueRepository()

    var leagueUiState by mutableStateOf(LeagueUiState())
        private set


    init {
        viewModelScope.launch {
//            leagueUiState = leaguesRepository.getAllLeaguesStream()
//                .filterNotNull()
//                .first()
//                .toLeagueUiState(true)
        }
    }
}


data class LeagueUiState(val leagueList: List<Club> = listOf())