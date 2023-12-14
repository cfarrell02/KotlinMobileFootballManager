package com.example.FootballApp.ui

import LeagueViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import org.setu.model.League


@Composable
fun LeaguesList(leagues: List<League>, onLeagueClick: (League) -> Unit) {
    LazyColumn {
        items(leagues) { league ->
            LeagueItemRow(league = league, onLeagueClick = { onLeagueClick(league) })
        }
    }
}

@Composable
fun LeagueItemRow(league: League, onLeagueClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onLeagueClick() }
    ) {
        // Display league crest (assuming you have a method to load image from URL)
        // Example: CoilImage(league.crestUrl, contentDescription = "League Crest", modifier = Modifier.size(50.dp))

        Text(text = league.name, modifier = Modifier.weight(1f))
        Text(text = league.country)
    }
}

@Composable
fun FloatingAddLeagueButton(onClick: () -> Unit) {
    //Bottom right floating action button
    //Modifier to place it in bottom right corner
    FloatingActionButton(onClick = { onClick() } , modifier = Modifier.padding(16.dp)) {
        Text(text = "+")
    }
}

@Composable
fun LeaguesScreen(leaguesViewModel: LeagueViewModel = viewModel(factory = LeagueViewModel.Factory)) {
val leaguesViewModel = viewModel<LeagueViewModel>()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val leagueState = leaguesViewModel.leagues.collectAsState()
        LeaguesList(leagues = leagueState.value, onLeagueClick = leaguesViewModel::onLeagueClicked)
        // Additional components or actions related to leagues can be added here

        FloatingAddLeagueButton(onClick = {
            val time = System.currentTimeMillis()
            leaguesViewModel.addLeague(League("Test", "Time at $time", "Test"))
        })

    }
}

