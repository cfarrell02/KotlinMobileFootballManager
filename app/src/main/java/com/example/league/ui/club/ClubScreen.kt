package com.example.league.ui.club

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.league.LeagueTopAppBar
import com.example.league.R
import com.example.league.ui.AppViewModelProvider
import com.example.league.ui.home.Loading
import com.example.league.ui.navigation.NavigationDestination
import org.setu.model.Club

object ClubDestination : NavigationDestination {
    override val route = "club/{clubId}"
    override val titleRes = R.string.club
    val clubId = "clubId"
    val routeWithArgs = "$route/{clubId}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClubScreen(navigateBack: () -> Unit, navigateToEditClub: (Int) -> Unit, viewModel: ClubViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val uiState = viewModel.clubUiState.collectAsState()

    Scaffold(
        topBar = { LeagueTopAppBar(title = stringResource(R.string.club_details), canNavigateBack = true, navigateUp = navigateBack) }
    ) {
        Column(modifier = Modifier.padding(it).verticalScroll(rememberScrollState())) {
            if (!uiState.value.isLoaded) {
                Loading()
            } else {
                var showDialog by remember { mutableStateOf(false) }
                ClubDetails(uiState.value.club, onDelete = {
                    showDialog = true
                }, onEdit = { navigateToEditClub(uiState.value.club.uid) })
                Spacer(modifier = Modifier.height(16.dp))
                ConfirmationDialog(showDialog, onConfirm = {
                    viewModel.deleteClub(uiState.value.club)
                    navigateBack()
                }, onDismiss = {
                    showDialog = false
                })
            }
        }
    }

}
@Composable
fun ClubEditDeleteButtons(onDelete : () -> Unit, onEdit : () -> Unit){
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
fun ClubDetails(club: Club, onDelete: () -> Unit, onEdit: () -> Unit) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = club.name,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
            AsyncImage(
                model = club.crestUrl,
                contentDescription = stringResource(R.string.club_logo),
                modifier = Modifier
                    .size(50.dp)
                    .padding(start = 4.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = club.country,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(text = "(${club.founded})",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        ClubEditDeleteButtons(onDelete = onDelete , onEdit = onEdit)
        Divider()
        VenueDetails(club = club)

    }
}

@Composable
fun VenueDetails(club: Club){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = stringResource(R.string.venue), style = MaterialTheme.typography.titleLarge)
                AsyncImage(
                    model = club.venueImageUrl,
                    contentDescription = stringResource(R.string.venue_image),
                    modifier = Modifier
                        .size(300.dp)
                        .padding(bottom = 8.dp)
                )
                Text(text = club.venue)
                Text(text = club.venueAddress)
                Text(text = club.venueCity)
                Text(text = stringResource(R.string.capacity_people, club.venueCapacity))
                Text(text = stringResource(
                    R.string.surface,
                    club.venueSurface.replaceFirstChar { it.uppercase() }))
            }

    }
}




@Composable
fun ConfirmationDialog(
    showDialog: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = stringResource(R.string.delete_this_club)) },
            text = { Text(text = stringResource(R.string.confirm_delete)) },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirm()
                        onDismiss()
                    }
                ) {
                    Text(text = stringResource(R.string.delete))
                }
            },
            dismissButton = {
                Button(onClick = onDismiss) {
                    Text(text = stringResource(R.string.cancel_action))
                }
            }
        )
    }
}