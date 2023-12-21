/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.league.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.league.LeagueApplication
import com.example.league.ui.club.ClubAddModel
import com.example.league.ui.club.ClubEditViewModel
import com.example.league.ui.club.ClubViewModel
import com.example.league.ui.home.HomeAddModel
import com.example.league.ui.home.HomeViewModel
import com.example.league.ui.home.SearchViewModel
import com.example.league.ui.league.LeagueEditViewModel
import com.example.league.ui.league.LeagueViewModel

//import com.example.inventory.ui.item.ItemDetailsViewModel
//import com.example.inventory.ui.item.ItemEditViewModel
//import com.example.inventory.ui.item.ItemEntryViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(leagueApplication().container.leagueRepository)
        }
        initializer {
            LeagueViewModel(this.createSavedStateHandle(), leagueApplication().container.leagueRepository, leagueApplication().container.clubsRepository)
        }
        initializer {
            LeagueEditViewModel(this.createSavedStateHandle(), leagueApplication().container.leagueRepository)
        }
        initializer {
            HomeAddModel(leagueApplication().container.leagueRepository)
        }
        initializer {
            ClubAddModel(this.createSavedStateHandle(), leagueApplication().container.clubsRepository)
        }
        initializer {
            ClubViewModel(this.createSavedStateHandle(), leagueApplication().container.clubsRepository)
        }
        initializer { ClubEditViewModel(this.createSavedStateHandle(), leagueApplication().container.clubsRepository) }
        initializer { SearchViewModel(leagueApplication().container.leagueRepository, leagueApplication().container.clubsRepository) }
    }

    /**
     * Extension function to queries for [Application] object and returns an instance of
     * [LeagueApplication].
     */
    fun CreationExtras.leagueApplication(): LeagueApplication =
        (this[AndroidViewModelFactory.APPLICATION_KEY] as LeagueApplication)

}
