package com.example.league.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.league.data.ClubRepository
import com.example.league.data.LeagueRepository
import com.example.league.model.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.setu.model.League

class SearchViewModel (private val leagueRepository: LeagueRepository, private val clubRepository: ClubRepository) : ViewModel(){
    private val _searchResults: MutableStateFlow<List<Item>> = MutableStateFlow(emptyList())
    val searchResults: StateFlow<List<Item>> = _searchResults

     fun search(query: String) {
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
        _searchResults.value = results

        }
    }

}
