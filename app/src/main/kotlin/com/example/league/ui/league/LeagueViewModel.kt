package com.example.league.ui.league

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.league.data.ClubRepository
import com.example.league.data.LeagueRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.setu.model.Club
import org.setu.model.League

class LeagueViewModel(
    savedStateHandle: SavedStateHandle,
    private val leaguesRepository: LeagueRepository,
    private val clubsRepository: ClubRepository
    ): ViewModel() {

    //private val leaguesRepository = OfflineLeagueRepository()


    private val leagueId: String =  checkNotNull(savedStateHandle[LeagueDestination.leagueId])

    val leagueUiState: StateFlow<LeagueUiState> =
        leaguesRepository.getLeagueStream(leagueId.toInt())
            .filterNotNull()
            .map {
                LeagueUiState(it, true)
            }.stateIn(
                scope = viewModelScope,
                started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = LeagueUiState()
            )
    val leagueClubUiState: StateFlow<LeagueClubUiState> =
        clubsRepository.getClubsByLeagueId(leagueId.toInt())
            .map {
                LeagueClubUiState(it, true)
            }.stateIn(
                scope = viewModelScope,
                started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = LeagueClubUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    fun deleteLeague(league: League) {
        viewModelScope.launch {
            leaguesRepository.deleteLeague(league)
            clubsRepository.deleteClubsByLeagueId(league.uid)
            // navigate back to home screen

        }
    }
}


data class LeagueUiState(val league: League = League(), val isLoaded: Boolean = false)
data class LeagueClubUiState(val clubs : List<Club> = emptyList(), val isLoaded: Boolean = false)