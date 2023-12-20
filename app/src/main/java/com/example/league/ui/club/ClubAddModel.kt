package com.example.league.ui.club

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.league.data.ClubRepository
import com.example.league.ui.league.LeagueDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.setu.model.Club

class ClubAddModel (savedStateHandle: SavedStateHandle, private val clubRepository: ClubRepository) : ViewModel() {

    private val leagueId: String =  checkNotNull(savedStateHandle[LeagueDestination.leagueId])


    private val _searchResults: MutableStateFlow<List<Club>> = MutableStateFlow(emptyList())
    val searchResults: StateFlow<List<Club>> = _searchResults

    fun insertClub(club: Club){
        viewModelScope.launch {
            club.leagueId = leagueId.toInt()
            clubRepository.insertClub(club)
        }
    }

    fun searchClub(query: String){
        viewModelScope.launch {
            _searchResults.value = clubRepository.searchClub(query)
        }
    }
}