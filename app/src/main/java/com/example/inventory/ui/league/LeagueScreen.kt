package com.example.inventory.ui.league

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.inventory.InventoryTopAppBar
import com.example.inventory.R
import com.example.inventory.ui.AppViewModelProvider
import com.example.inventory.ui.navigation.NavigationDestination
import org.setu.model.Club
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
    navigateToClubAdd: (Int) -> Unit,
    navigateBack : () -> Unit,
    navigateToClubDetails : (Int) -> Unit,
    viewModel: LeagueViewModel = viewModel(factory = AppViewModelProvider.Factory),
){
    val uiState = viewModel.leagueUiState.collectAsState()
    val uiClubState = viewModel.leagueClubUiState.collectAsState()

    Scaffold (
        topBar = {
            InventoryTopAppBar(title = stringResource(R.string.league_details), canNavigateBack =true, navigateUp = navigateBack )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToClubAdd(uiState.value.league.uid) },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_club)
                )
            }
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


            // Clubs
            if (uiClubState.value.clubs.isNotEmpty()) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.Center // Added to center the Row content horizontally
                    ) {
                        Text(
                            text = stringResource(R.string.clubs),
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                        )
                    }
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    )
                    ClubList(clubs = uiClubState.value.clubs, onClubPress = navigateToClubDetails)
                }
            }else{
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.Center // Added to center the Row content horizontally
                ) {
                    Text(
                        text = stringResource(R.string.no_clubs),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                    )
                }
            }
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

@Composable
fun ClubList(clubs: List<Club>, onClubPress : (Int) -> Unit = {}) {
    Column {
        clubs.forEach { club ->
            ClubRow(club, onClubPress)
            Divider()
        }
    }
}

@Composable
fun ClubRow(club: Club, onPress : (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onPress(club.uid) }
    ) {
        Row(
            modifier = Modifier
                .padding(start = 80.dp, end = 80.dp, top = 16.dp, bottom = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // Aligns content at ends
        ) {

            Column {
                Text(
                    text = club.name,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = club.country,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(club.crestUrl)
                    .build(),
                contentDescription = stringResource(R.string.league_logo_alt, club.name),
            )
        }
    }

}


