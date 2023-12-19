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

package com.example.inventory.data

import android.content.Context
import com.example.inventory.network.LeagueAPIService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val itemsRepository: LeagueRepository
    val clubsRepository: OfflineClubRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    private val baseUrl = "https://api-football-v1.p.rapidapi.com/v3/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()
    private val retrofitService: LeagueAPIService by lazy {
        retrofit.create(LeagueAPIService::class.java)
    }
    override val itemsRepository: LeagueRepository by lazy {
        OfflineLeagueRepository(InventoryDatabase.getDatabase(context).leagueDao(), retrofitService)
    }
    override val clubsRepository: OfflineClubRepository by lazy {
        OfflineClubRepository(InventoryDatabase.getDatabase(context).clubDao())
    }
}
