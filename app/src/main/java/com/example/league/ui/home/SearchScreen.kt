package com.example.league.ui.home

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    val results = stateFlow.value.searchResults
    Scaffold (
        topBar = { LeagueTopAppBar(title = stringResource(R.string.search), canNavigateBack = true, navigateUp = navigateBack)}
    ){
        Column (modifier = Modifier.padding(it), horizontalAlignment = Alignment.CenterHorizontally){
            SearchBar(onSearch =  viewModel::search)
            if (stateFlow.value.isLoaded) {
                SearchResults(results, navigateToClub, navigateToLeague)
            } else {
                Loading()
            }
        }
    }

}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar (onSearch : (String) -> Unit = {}) {
    var query by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column {
        OutlinedTextField(
            value = query, onValueChange = { query = it },
            label = { Text(text = stringResource(id = R.string.search)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true,
            keyboardActions = KeyboardActions(onDone = { onSearch(query)
                hideKeyboard(context)

            })
        )

        Button(onClick = { onSearch(query)
            hideKeyboard(context)
        }, modifier = Modifier.padding(16.dp)) {
            Text(text = stringResource(id = R.string.search))
        }
    }


}

fun hideKeyboard(context: Context) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(
        (context as? Activity)?.currentFocus?.windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
    )
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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (item is Club) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { navigateToClub(item.uid) }) {
                Text(text = stringResource(R.string.club_and_name, item.name))
                Spacer(modifier = Modifier.weight(1f))
                Text(text = item.country)
            }
        } else if (item is League) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { navigateToLeague(item.uid) }) {
                Text(text = stringResource(R.string.league_and_name, item.name))
                Spacer(modifier = Modifier.weight(1f))
                Text(text = item.country)
            }
        }
    }
}