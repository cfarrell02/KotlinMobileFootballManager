package com.example.league.ui.club

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.league.data.ClubRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.setu.model.Club

class ClubEditViewModel (
    savedStateHandle: SavedStateHandle,
    private val clubRepository: ClubRepository
): ViewModel(){


         private val clubId: String = checkNotNull(savedStateHandle[ClubDestination.clubId])

        private var _clubUiState by mutableStateOf(ClubUiState())
        val clubUiState: ClubUiState
                get() = _clubUiState

            init {
                viewModelScope.launch {
                    _clubUiState = clubRepository.getClubStream(clubId.toInt())
                        .filterNotNull().first().let { ClubUiState(it)}
                }
        }

    fun updateClubUiState(club: Club) {
        _clubUiState = _clubUiState.copy(club = club)
    }

    fun updateClub(club: Club) {
        viewModelScope.launch {
            clubRepository.updateClub(club)
        }
    }



}

