package com.example.league.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.league.model.Item
import com.example.league.ui.AppViewModelProvider
import com.example.league.ui.navigation.NavigationDestination
import org.setu.model.Club
import org.setu.model.League

object SearchDestination: NavigationDestination {
    override val route = "search"
    override val titleRes = 0
    val routeWithArgs = route
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(viewModel : SearchViewModel = viewModel(factory = AppViewModelProvider.Factory),
                 navigateToClub : (Int) -> Unit, navigateToLeague : (Int) -> Unit, navigateBack : () -> Unit) {
var stateFlow = viewModel.searchResults.collectAsState()
    Scaffold (
        topBar = { LeagueTopAppBar(title = stringResource(R.string.search), canNavigateBack = true, navigateUp = navigateBack)}
    ){
        Column (modifier = Modifier.padding(it), horizontalAlignment = Alignment.CenterHorizontally){
            SearchBar(onSearch =  viewModel::search)
            SearchResults(stateFlow.value, navigateToClub, navigateToLeague)
        }
    }

}

@Composable
fun SearchBar (onSearch : (String) -> Unit = {}) {
    var query by remember { mutableStateOf("") }
    Column {
        OutlinedTextField(
            value = query, onValueChange = { query = it },
            label = { Text(text = stringResource(id = R.string.search)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Button(onClick = { onSearch(query) }, modifier = Modifier.padding(16.dp)) {
            Text(text = stringResource(id = R.string.search))
        }
    }


}

@Composable
fun SearchResults (searchResults: List<Item>, navigateToClub : (Int) -> Unit, navigateToLeague : (Int) -> Unit) {
    LazyColumn {
        items(searchResults) { item ->
            SearchItem(item = item, navigateToClub = navigateToClub, navigateToLeague = navigateToLeague)
        }
    }
}

@Composable
fun SearchItem (item : Item, navigateToClub : (Int) -> Unit, navigateToLeague : (Int) -> Unit) {
    if (item is Club) {
        Text(text = item.name)
    } else if (item is League) {
        Text(text = item.name)
    }
}