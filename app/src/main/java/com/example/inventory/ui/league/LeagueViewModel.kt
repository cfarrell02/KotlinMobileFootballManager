package com.example.inventory.ui.league

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.setu.model.League

//class LeagueViewModel()
//    var leagueUiState by mutableStateOf(LeagueUiState())
//        private set
//
//    private val leagueId: Int = checkNotNull(savedStateHandle[LeagueDestination.leagueIdArg])
//
//    init {
//        viewModelScope.launch {
//            leagueUiState = leaguesRepository.getLeagueStream(leagueId)
//                .filterNotNull()
//                .first()
//                .toLeagueUiState(true)
//        }
//    }
//}


data class LeagueUiState(val leagueList: List<League> = listOf())