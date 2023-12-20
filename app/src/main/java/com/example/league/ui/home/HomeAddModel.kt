package com.example.league.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.league.data.LeagueRepository
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
    private val _searchResults: MutableStateFlow<List<League>> = MutableStateFlow(emptyList())
    val searchResults: StateFlow<List<League>> = _searchResults

    fun searchLeague(leagueName: String) {
        viewModelScope.launch {
            _searchResults.value = leaguesRepository.searchLeague(leagueName)
        }
    }

}