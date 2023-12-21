package com.example.league.ui.club

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.league.LeagueTopAppBar
import com.example.league.R
import com.example.league.ui.AppViewModelProvider
import com.example.league.ui.navigation.NavigationDestination
import org.setu.model.Club


object ClubEditDestination : NavigationDestination {
    override val route = "club_edit"
    override val titleRes = R.string.club
    val clubId = "clubId"
    val routeWithArgs = "$route/{clubId}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClubEditScreen(
    viewModel: ClubEditViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateBack: () -> Unit
) {

    Scaffold(
        topBar = { LeagueTopAppBar(title = stringResource(R.string.club_details), canNavigateBack = true, navigateUp = navigateBack) }
    ) {
        Column (modifier = Modifier.padding(it).verticalScroll(rememberScrollState())) {
            ClubEdit(club = viewModel.clubUiState.club, onClubChange = { viewModel.updateClubUiState(it) })
            ClubEditSaveButton(onSave = { viewModel.updateClub(viewModel.clubUiState.club)
                navigateBack()})
        }
    }
}

@Composable
fun ClubEditSaveButton(onSave: () -> Unit) {
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
fun ClubEdit(club: Club, onClubChange : (Club) -> Unit) {

    var name by remember { mutableStateOf(club.name) }
    var country by remember { mutableStateOf(club.country) }
    var founded by remember { mutableStateOf(club.founded) }
    var venue by remember { mutableStateOf(club.venue) }
    var venueAddress by remember { mutableStateOf(club.venueAddress) }
    var venueCity by remember { mutableStateOf(club.venueCity) }
    var venueCapacity by remember { mutableStateOf(club.venueCapacity) }
    var venueSurface by remember { mutableStateOf(club.venueSurface) }

    LaunchedEffect(club) {
        name = club.name
        country = club.country
        founded = club.founded
        venue = club.venue
        venueAddress = club.venueAddress
        venueCity = club.venueCity
        venueCapacity = club.venueCapacity
        venueSurface = club.venueSurface
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                onClubChange(Club(name, club.code ,country, club.national, founded, club.crestUrl,
                    venue, venueAddress, venueCity, venueCapacity, venueSurface, club.venueImageUrl, club.leagueId, club.uid))
            },
            label = { Text(stringResource(R.string.club_name)) },
            isError = name.isEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = country,
            onValueChange = {
                country = it
                onClubChange(Club(name, club.code ,country, club.national, founded, club.crestUrl,
                    venue, venueAddress, venueCity, venueCapacity, venueSurface, club.venueImageUrl, club.leagueId, club.uid))
            },
            label = { Text(stringResource(R.string.club_country)) },
            isError = country.isEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = founded.toString(),
            onValueChange = {
                founded = it.toIntOrNull() ?: 0
                onClubChange(Club(name, club.code ,country, club.national, founded, club.crestUrl,
                    venue, venueAddress, venueCity, venueCapacity, venueSurface, club.venueImageUrl, club.leagueId, club.uid))
            },
            label = { Text(stringResource(R.string.club_founded_year)) },
            isError = founded.toString().isEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = venue,
            onValueChange = {
                venue = it
                onClubChange(Club(name, club.code, country, club.national, founded, club.crestUrl,
                    venue, venueAddress, venueCity, venueCapacity, venueSurface,  club.venueImageUrl, club.leagueId, club.uid))
            },
            label = { Text(stringResource(R.string.club_venue)) },
            isError = venue.isEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = venueAddress,
            onValueChange = {
                venueAddress = it
                onClubChange(Club(name, club.code, country, club.national, founded, club.crestUrl,
                    venue, venueAddress, venueCity, venueCapacity, venueSurface,  club.venueImageUrl, club.leagueId, club.uid))
            },
            label = { Text(stringResource(R.string.club_venue_address)) },
            isError = venueAddress.isEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = venueCity,
            onValueChange = {
                venueCity = it
                onClubChange(
                    Club(
                        name,
                        club.code,
                        country,
                        club.national,
                        founded,
                        club.crestUrl,
                        venue,
                        venueAddress,
                        venueCity,
                        venueCapacity,
                        venueSurface,
                        club.venueImageUrl,
                        club.leagueId,
                        club.uid
                    )
                )
            },
            label = { Text(stringResource(R.string.club_venue_city)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = venueCapacity.toString(),
            onValueChange = {
                venueCapacity = it.toIntOrNull() ?: 0
                onClubChange(Club(name, club.code, country, club.national, founded, club.crestUrl,
                    venue, venueAddress, venueCity, venueCapacity, venueSurface, club.venueImageUrl, club.leagueId, club.uid))
            },
            label = { Text(stringResource(R.string.club_venue_capacity)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = venueSurface,
            onValueChange = {
                venueSurface = it
                onClubChange(Club(name, club.code, country, club.national, founded, club.crestUrl,
                    venue, venueAddress, venueCity, venueCapacity, venueSurface,  club.venueImageUrl, club.leagueId, club.uid))
            },
            label = { Text(stringResource(R.string.club_venue_surface)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

    }
}