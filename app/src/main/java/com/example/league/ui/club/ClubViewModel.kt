package com.example.league.ui.club

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.league.data.ClubRepository
import com.example.league.data.PlayerRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.setu.model.Club
import org.setu.model.Player

class ClubViewModel (savedStateHandle: SavedStateHandle,
                     private val clubRepository: ClubRepository,
                    private val playerRepository: PlayerRepository
    ) :ViewModel() {
    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }

         private val clubId : String = checkNotNull(savedStateHandle[ClubDestination.clubId])

            val clubUiState : StateFlow<ClubUiState> = clubRepository.getClubStream(clubId.toInt())
                .map {
                    ClubUiState(it ?: Club())
                }.stateIn(
                    scope = viewModelScope,
                    started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                    initialValue = ClubUiState()
                )
            val playerUiState : StateFlow<PlayerUiState> = playerRepository.getAllPlayersByTeamStream(clubId.toInt())
                .map {
                    PlayerUiState(it)
                }.stateIn(
                    scope = viewModelScope,
                    started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                    initialValue = PlayerUiState()
                )

    fun deleteClub(club: Club){
        viewModelScope.launch {
            clubRepository.deleteClub(club)
        }
    }


}

data class ClubUiState (val club: Club = Club())
data class PlayerUiState (val player: List<Player> = emptyList())