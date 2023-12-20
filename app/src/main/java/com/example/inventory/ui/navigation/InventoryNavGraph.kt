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

package com.example.inventory.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.inventory.ui.home.HomeAddDestination
import com.example.inventory.ui.home.HomeAddScreen
import com.example.inventory.ui.home.HomeDestination
import com.example.inventory.ui.home.HomeScreen
import com.example.inventory.ui.league.LeagueDestination
import com.example.inventory.ui.league.LeagueEditDestination
import com.example.inventory.ui.league.LeagueEditScreen
import com.example.inventory.ui.league.LeagueScreen

//import com.example.inventory.ui.item.ItemDetailsDestination
//import com.example.inventory.ui.item.ItemDetailsScreen
//import com.example.inventory.ui.item.ItemEditDestination
//import com.example.inventory.ui.item.ItemEditScreen
//import com.example.inventory.ui.item.ItemEntryDestination
//import com.example.inventory.ui.item.ItemEntryScreen

/**
 * Provides Navigation graph for the application.
 */
@Composable
fun InventoryNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToLeagueUpdate = {
                    navController.navigate("${LeagueDestination.route}/$it")
                },
                navigateToLeagueAdd = {
                    navController.navigate(HomeAddDestination.route)
                }
            )
        }
        composable(route = LeagueDestination.routeWithArgs,
            arguments = listOf(
                navArgument(LeagueDestination.leagueId) {
                    type = NavType.StringType
                }
            )
        ) {

            LeagueScreen( navigateToItemEdit = {
                navController.navigate("${LeagueEditDestination.route}/$it")
                                               },
                navigateBack = { navController.popBackStack() },
                )
        }
        composable(route = LeagueEditDestination.routeWithArgs,
                arguments = listOf(
                navArgument(LeagueEditDestination.leagueId) {
                    type = NavType.StringType
                }
            )
            ) {
            LeagueEditScreen( navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
                )
        }
        composable(route = HomeAddDestination.routeWithArgs
        ) {
            HomeAddScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

    }
}
