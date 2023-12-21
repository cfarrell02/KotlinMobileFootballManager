package com.example.league.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.league.data.ClubRepository
import com.example.league.data.LeagueRepository
import com.example.league.model.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SearchViewModel (private val leagueRepository: LeagueRepository, private val clubRepository: ClubRepository) : ViewModel(){
    private val _searchResults: MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState())
    val searchResults: StateFlow<SearchUiState> = _searchResults

     fun search(query: String) {
         _searchResults.value = SearchUiState(isLoaded = false)
         viewModelScope.launch {
        val results = mutableListOf<Item>()
        leagueRepository.getAllLeaguesStream().first().forEach {
            if (it.name.contains(query, ignoreCase = true)) {
                results.add(it)
            }
        }
        clubRepository.getAllClubsStream().first().forEach {
            if (it.name.contains(query, ignoreCase = true)) {
                results.add(it)
            }
        }
        _searchResults.value = SearchUiState(results, true)

        }
    }

}

data class SearchUiState(val searchResults: List<Item> = listOf(), val isLoaded : Boolean = true)
