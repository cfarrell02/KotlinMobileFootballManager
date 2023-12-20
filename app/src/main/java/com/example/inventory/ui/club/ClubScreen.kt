package com.example.inventory.ui.club

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
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
import org.setu.model.Club

object ClubDestination : NavigationDestination {
    override val route = "club/{clubId}"
    override val titleRes = R.string.club
    val clubId = "clubId"
    val routeWithArgs = "$route/{clubId}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClubScreen(navigateBack: () -> Unit, viewModel: ClubViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val uiState = viewModel.clubUiState.collectAsState()

    Scaffold(
        topBar = { InventoryTopAppBar(title = stringResource(R.string.club_details), canNavigateBack = true, navigateUp = navigateBack) }
    ) {
        Column(modifier = Modifier.padding(it)) {
            ClubDetails(uiState.value.club)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}



@Composable
fun ClubDetails(club: Club) {
    Column(
        modifier = Modifier.padding(16.dp)
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
                contentDescription = "Club logo",
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
        }

        // Display other club details in rows or columns as needed
        // For example:
        Text(text = "Venue: ${club.venue}")
        Text(text = "Venue Address: ${club.venueAddress}")
        Text(text = "Venue City: ${club.venueCity}")
        Text(text = "Venue Capacity: ${club.venueCapacity}")
        Text(text = "Venue Surface: ${club.venueSurface}")
        // Add more details as necessary
    }
}
