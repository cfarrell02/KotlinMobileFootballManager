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

        package com.example.inventory.ui.home

        import android.annotation.SuppressLint
        import androidx.compose.foundation.*
        import androidx.compose.foundation.layout.Arrangement
        import androidx.compose.foundation.layout.Column
        import androidx.compose.foundation.layout.Row
        import androidx.compose.foundation.layout.fillMaxWidth
        import androidx.compose.foundation.layout.padding
        import androidx.compose.foundation.lazy.LazyColumn
        import androidx.compose.foundation.lazy.items
        import androidx.compose.material.icons.Icons
        import androidx.compose.material.icons.filled.Add
        import androidx.compose.material3.AlertDialog
        import androidx.compose.material3.Button
        import androidx.compose.material3.Card
        import androidx.compose.material3.CardDefaults
        import androidx.compose.material3.ExperimentalMaterial3Api
        import androidx.compose.material3.FloatingActionButton
        import androidx.compose.material3.Icon
        import androidx.compose.material3.MaterialTheme
        import androidx.compose.material3.OutlinedTextField
        import androidx.compose.material3.Scaffold
        import androidx.compose.material3.Text
        import androidx.compose.material3.TopAppBarDefaults
        import androidx.compose.runtime.Composable
        import androidx.compose.runtime.collectAsState
        import androidx.compose.runtime.getValue
        import androidx.compose.runtime.mutableStateOf
        import androidx.compose.runtime.remember
        import androidx.compose.runtime.setValue
        import androidx.compose.ui.Alignment
        import androidx.compose.ui.Modifier
        import androidx.compose.ui.input.nestedscroll.nestedScroll
        import androidx.compose.ui.platform.LocalContext
        import androidx.compose.ui.res.dimensionResource
        import androidx.compose.ui.res.stringResource
        import androidx.compose.ui.text.style.TextAlign
        import androidx.compose.ui.unit.dp
        import androidx.lifecycle.viewmodel.compose.viewModel
        import coil.compose.AsyncImage
        import coil.request.ImageRequest
        import com.example.inventory.InventoryTopAppBar
        import com.example.inventory.R
        import com.example.inventory.ui.AppViewModelProvider
        import com.example.inventory.ui.navigation.NavigationDestination
        import org.setu.model.League

        object HomeDestination : NavigationDestination {
            override val route = "home"
            override val titleRes = R.string.app_name
        }

        /**
         * Entry route for Home screen
         */
        @OptIn(ExperimentalMaterial3Api::class)
        @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
        @Composable
        fun HomeScreen(
            navigateToLeagueEntry: () -> Unit,
            navigateToLeagueUpdate: (Int) -> Unit,
            modifier: Modifier = Modifier,
            viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
        ){
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
            val homeUiState by viewModel.homeUiState.collectAsState()
            var isDialogOpen by remember { mutableStateOf(false) }

            Scaffold(
                modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    InventoryTopAppBar(
                        title = stringResource(HomeDestination.titleRes),
                        canNavigateBack = false,
                        scrollBehavior = scrollBehavior
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { isDialogOpen = true },
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(R.string.add_league)
                        )
                    }
                },
            ) { innerPadding ->

                HomeBody(
                    leagueList = homeUiState.leagueList,
                    onItemClick = navigateToLeagueUpdate,
                    modifier = modifier.padding(innerPadding)
                )
                if (isDialogOpen) {
                    AddLeagueDialog(
                        onLeagueAdded = { name ->
                            viewModel.searchLeague(name)
                            isDialogOpen = false
                        },
                        onDismiss = { isDialogOpen = false },

                    )
                }

            }
        }

        @Composable
        private fun HomeBody(
            leagueList: List<League>, onItemClick: (Int) -> Unit, modifier: Modifier = Modifier
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
            ) {
                if (leagueList.isEmpty()) {
                    Text(
                        text = stringResource(R.string.no_league_description),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge
                    )
                } else {
                    LeagueList(
                        leagueList = leagueList,
                        onItemClick = { onItemClick(it) },
                        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
                    )
                }
            }
        }

        @Composable
        private fun LeagueList(
            leagueList: List<League>, onItemClick: (Int) -> Unit, modifier: Modifier = Modifier
        ) {
            LazyColumn(modifier = modifier) {
                items(leagueList) { league ->
                    LeagueItem(
                        league = league,
                        onItemClick = { onItemClick(league.uid) },
                        modifier = Modifier
                            .fillMaxWidth().padding(dimensionResource(id = R.dimen.padding_small))
                            .clickable { onItemClick(league.uid) }
                    )
                }
            }
        }

        @Composable
        private fun LeagueItem(
            league: League, onItemClick: (Int) -> Unit, modifier: Modifier = Modifier
        ) {
            Card(
                modifier = modifier,
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Display League name, country, and crestUrl in an Image item
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
                    ) {
                        Text(
                            text = league.name,
                            style = MaterialTheme.typography.titleLarge,
                        )
                        Text(
                            text = league.country,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(league.crestUrl)
                            .build(),
                        contentDescription = stringResource(R.string.league_logo_alt, league.name),
                    )
                }

            }
        }

        @Composable
        fun AddLeagueDialog(
            onLeagueAdded: (String) -> Unit,
            onDismiss: () -> Unit
        ) {

            var searchQuery by remember { mutableStateOf("") }

            AlertDialog(
                onDismissRequest = onDismiss,
                title = { Text(text = "Add League") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            label = { Text("League Name") }
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            //val newLeague = League(name = name, country = country, crestUrl = crestUrl)
                            onLeagueAdded(searchQuery)
                            onDismiss()
                        }
                    ) {
                        Text(stringResource(id = R.string.add_league))
                    }
                },
                dismissButton = {
                    Button(onClick = onDismiss) {
                        Text(stringResource(id = R.string.cancel_action))
                    }
                }
            )
        }


