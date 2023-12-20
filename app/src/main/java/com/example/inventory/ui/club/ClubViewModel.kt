package com.example.inventory.ui.club

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.OfflineClubRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.setu.model.Club

class ClubViewModel (savedStateHandle: SavedStateHandle,
                     private val clubRepository: OfflineClubRepository) :ViewModel() {
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

}

data class ClubUiState (val club: Club = Club())