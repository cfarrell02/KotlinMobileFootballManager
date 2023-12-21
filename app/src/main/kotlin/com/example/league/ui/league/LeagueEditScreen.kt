package com.example.league.ui.league

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.league.LeagueTopAppBar
import com.example.league.R
import com.example.league.ui.AppViewModelProvider
import com.example.league.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch
import org.setu.model.League

object LeagueEditDestination : NavigationDestination {
    override val route = "league_edit"
    val routeWithArgs = "$route/{leagueId}"
    override val titleRes = R.string.app_name
    const val leagueId = "leagueId"
}

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeagueEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    viewModel: LeagueEditViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold (
        topBar = {
            LeagueTopAppBar(title = stringResource(id = R.string.edit_league), canNavigateBack =true, navigateUp = onNavigateUp )
        }
    ) {
        innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).verticalScroll(rememberScrollState())) {
            LeagueEdit(
                league = viewModel.leagueUiState.league,
                onLeagueChange = { viewModel.updateLeagueUiState(it) }
            )
            LeagueEditSaveButton(
                onSave = {
                    coroutineScope.launch {
                        viewModel.updateLeague()
                        navigateBack()
                    }
                }
            )
        }
    }
}

@Composable
fun LeagueEditSaveButton(onSave: () -> Unit) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.Center
    ){
        Button(onClick = onSave){
            Text(stringResource(R.string.update_league))
        }
    }
}

@Composable
fun LeagueEdit(league: League, onLeagueChange: (League) -> Unit, onCrestChange: () -> Unit = {}) {
    var name by remember { mutableStateOf(league.name) }
    var country by remember { mutableStateOf(league.country) }
    var type by remember { mutableStateOf(league.type) }


    // Update state when league changes
    LaunchedEffect(league) {
        name = league.name
        country = league.country
        type = league.type
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                onLeagueChange(League(name, country, type, league.crestUrl, league.countryFlagUrl, league.uid))
            },
            label = { Text(stringResource(R.string.league_name)) },
            isError = name.isEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = country,
            onValueChange = {
                country = it
                onLeagueChange(League(name, country, type, league.crestUrl, league.countryFlagUrl, league.uid))
            },
            label = { Text(stringResource(R.string.league_country)) },
            isError = country.isEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = type,
            onValueChange = {
                type = it
                onLeagueChange(League(name, country, type, league.crestUrl, league.countryFlagUrl, league.uid))
            },
            isError = type.isEmpty(),
            label = { Text(stringResource(R.string.league_type)) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}


