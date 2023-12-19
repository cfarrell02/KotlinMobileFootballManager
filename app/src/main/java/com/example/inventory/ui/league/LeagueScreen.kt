package com.example.inventory.ui.league

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.inventory.InventoryTopAppBar
import com.example.inventory.R
import com.example.inventory.ui.AppViewModelProvider
import com.example.inventory.ui.navigation.NavigationDestination
import org.setu.model.League

object LeagueDestination : NavigationDestination {
    override val route = "league"
    val routeWithArgs = "$route/{leagueId}"
    override val titleRes = R.string.app_name
    const val leagueId = "leagueId"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeagueScreen(
    navigateToItemEdit: (Int) -> Unit,
    navigateBack : () -> Unit,
    viewModel: LeagueViewModel = viewModel(factory = AppViewModelProvider.Factory),
){
    val uiState = viewModel.leagueUiState.collectAsState()

    Scaffold (
        topBar = {
            InventoryTopAppBar(title = stringResource(R.string.league_details), canNavigateBack =true, navigateUp = navigateBack )
        }
    ) {
        innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            LeagueDetails(
                league = uiState.value.league
            )
            LeagueEditDeleteButtons(
                onDelete = {
                    viewModel.deleteLeague(uiState.value.league)
                    navigateBack()
                },
                onEdit = { navigateToItemEdit(uiState.value.league.uid) }
            )

            // Divider
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }
    }

}

@Composable
fun LeagueDetails(league: League) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = league.name,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
            AsyncImage(
                model = league.crestUrl,
                contentDescription = "League logo",
                modifier = Modifier
                    .size(50.dp)
                    .padding(start = 4.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center // Added to center the Row content horizontally
        ) {
            Text(
                text = league.country,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun LeagueEditDeleteButtons(onDelete : () -> Unit, onEdit : () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.Center // Added to center the Row content horizontally
    ) {
        Button(onClick = { onEdit() },
            modifier = Modifier.padding(end = 8.dp)
            ) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = stringResource(R.string.edit))
        }
        Button(onClick = { onDelete() },
            modifier = Modifier.padding(start = 8.dp)
            ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = stringResource(id = R.string.delete))
        }
    }
}


