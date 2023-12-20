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

        package com.example.league.ui.home

        import android.annotation.SuppressLint
        import androidx.compose.foundation.*
        import androidx.compose.foundation.layout.Arrangement
        import androidx.compose.foundation.layout.Column
        import androidx.compose.foundation.layout.Row
        import androidx.compose.foundation.layout.fillMaxSize
        import androidx.compose.foundation.layout.fillMaxWidth
        import androidx.compose.foundation.layout.padding
        import androidx.compose.foundation.lazy.LazyColumn
        import androidx.compose.foundation.lazy.items
        import androidx.compose.material.icons.Icons
        import androidx.compose.material.icons.filled.Add
        import androidx.compose.material.icons.filled.Search
        import androidx.compose.material3.Card
        import androidx.compose.material3.CardDefaults
        import androidx.compose.material3.ExperimentalMaterial3Api
        import androidx.compose.material3.FabPosition
        import androidx.compose.material3.FloatingActionButton
        import androidx.compose.material3.Icon
        import androidx.compose.material3.MaterialTheme
        import androidx.compose.material3.Scaffold
        import androidx.compose.material3.Text
        import androidx.compose.material3.TopAppBarDefaults
        import androidx.compose.runtime.Composable
        import androidx.compose.runtime.collectAsState
        import androidx.compose.runtime.getValue
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
        import com.example.league.LeagueTopAppBar
        import com.example.league.R
        import com.example.league.ui.AppViewModelProvider
        import com.example.league.ui.navigation.NavigationDestination
        import org.setu.model.League


        object HomeDestination : NavigationDestination {
            override val route = "league"
            override val titleRes = R.string.app_name
            val routeWithArgs = route
        }

        /**
         * Entry route for Home screen
         */
        @OptIn(ExperimentalMaterial3Api::class)
        @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
        @Composable
        fun HomeScreen(
            navigateToLeagueUpdate: (Int) -> Unit,
            navigateToLeagueAdd: () -> Unit,
            modifier: Modifier = Modifier,
            navigateToSearch: () -> Unit,
            viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
        ){
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
            val homeUiState by viewModel.homeUiState.collectAsState()

            Scaffold(
                modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    LeagueTopAppBar(
                        title = stringResource(HomeDestination.titleRes),
                        canNavigateBack = false,
                        scrollBehavior = scrollBehavior
                    )
                },
                floatingActionButton = {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(id = R.dimen.padding_small)),
                        horizontalArrangement = Arrangement.SpaceBetween // Aligns content at ends
                    ) {

                        FloatingActionButton(
                            onClick = { navigateToSearch() },
                            shape = MaterialTheme.shapes.medium,
                            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = stringResource(R.string.search_league)
                            )
                        }

                        FloatingActionButton(
                            onClick = { navigateToLeagueAdd() },
                            shape = MaterialTheme.shapes.medium,
                            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = stringResource(R.string.add_league)
                            )
                        }
                    }

                },
                floatingActionButtonPosition = FabPosition.Center
            ) { innerPadding ->

                HomeBody(
                    leagueList = homeUiState.leagueList,
                    onItemClick = navigateToLeagueUpdate,
                    modifier = modifier.padding(innerPadding)
                )


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
                    Column( verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                    Text(
                        text = stringResource(R.string.no_league_description),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    }
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(id = R.dimen.padding_small))
                            .clickable { onItemClick(league.uid) }
                    )
                }
            }
        }

        @Composable
        private fun LeagueItem(
            league: League,
            modifier: Modifier = Modifier
        ) {
            Card(
                modifier = modifier,
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_large))
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween // Aligns content at ends
                ) {
                    // Display League name and country
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(
                            dimensionResource(id = R.dimen.padding_small)
                        )
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
