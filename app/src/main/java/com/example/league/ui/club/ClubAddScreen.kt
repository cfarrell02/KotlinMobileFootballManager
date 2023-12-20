package com.example.league.ui.club



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import com.example.league.LeagueTopAppBar
import com.example.league.R
import com.example.league.ui.AppViewModelProvider
import com.example.league.ui.navigation.NavigationDestination
import org.setu.model.Club

object ClubAddDestination : NavigationDestination {
    override val route = "club_add"
    override val titleRes = R.string.clubs
    val leagueId = "leagueId"
    val routeWithArgs = "$route/{leagueId}"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClubAddScreen(
    navigateBack: () -> Unit,
    viewModel: ClubAddModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    var searchQuery by remember { mutableStateOf("") }
    val searchResults by viewModel.searchResults.collectAsState()

    Scaffold(
        topBar = { LeagueTopAppBar(title = "Add Club", canNavigateBack = true) }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Column (modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Search for a club") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (searchResults.isNotEmpty()) {
                    LazyColumn {
                        items(searchResults) { club ->
                            ClubItem(
                                club = club,
                                onAddClick = {
                                    viewModel.insertClub(club)
                                    navigateBack()
                                }
                            )
                        }
                    }
                } else {
                    Text(
                        text = "No search results found.",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { viewModel.searchClub(searchQuery) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 4.dp)
                    ) {
                        Text(stringResource(id = R.string.search_club))
                    }
                    Button(
                        onClick = navigateBack,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp)
                    ) {
                        Text(stringResource(id = R.string.cancel_action))
                    }
                }
            }
        }
    }
}

@Composable
fun ClubItem(club: Club, onAddClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = club.name,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = club.country,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Button(
                onClick = onAddClick,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(stringResource(id = R.string.add_club))
            }
        }
    }
}

