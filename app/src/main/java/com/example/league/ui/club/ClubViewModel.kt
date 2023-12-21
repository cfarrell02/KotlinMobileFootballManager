package com.example.league.ui.club

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.league.data.ClubRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.setu.model.Club

class ClubViewModel (savedStateHandle: SavedStateHandle,
                     private val clubRepository: ClubRepository ) :ViewModel() {
    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }

         private val clubId : String = checkNotNull(savedStateHandle[ClubDestination.clubId])

            val clubUiState : StateFlow<ClubUiState> = clubRepository.getClubStream(clubId.toInt())
                .map {
                    ClubUiState(it ?: Club(), true)
                }.stateIn(
                    scope = viewModelScope,
                    started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                    initialValue = ClubUiState()
                )

    fun deleteClub(club: Club){
        viewModelScope.launch {
            clubRepository.deleteClub(club)
        }
    }


}

data class ClubUiState (val club: Club = Club(), val isLoaded: Boolean = false)
