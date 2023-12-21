package com.example.league.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.league.data.LeagueRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.setu.model.League

class HomeAddModel(
    private val leaguesRepository: LeagueRepository
) : ViewModel()
{
    fun addLeague(league: League) {
        viewModelScope.launch {
            leaguesRepository.insertLeague(league)
        }
    }
    private val _searchResults: MutableStateFlow<HomeAddUiState> = MutableStateFlow(HomeAddUiState(isLoaded = true))
    val searchResults: StateFlow<HomeAddUiState> = _searchResults

    fun searchLeague(leagueName: String) {
        _searchResults.value = HomeAddUiState(isLoaded = false)
        viewModelScope.launch {
            _searchResults.value = HomeAddUiState(leaguesRepository.searchLeague(leagueName), true)
        }
    }

}

data class HomeAddUiState(val leagueList: List<League> = listOf(), val isLoaded : Boolean = true)