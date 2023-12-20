package com.example.league.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.league.LeagueTopAppBar
import com.example.league.R
import com.example.league.ui.AppViewModelProvider
import com.example.league.ui.navigation.NavigationDestination

object SearchDestination: NavigationDestination {
    override val route = "search"
    override val titleRes = 0
    val routeWithArgs = route
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(viewModel : SearchViewModel = viewModel(factory = AppViewModelProvider.Factory),
                 navigateToClub : (Int) -> Unit, navigateToLeague : (Int) -> Unit, navigateBack : () -> Unit) {

    Scaffold (
        topBar = { LeagueTopAppBar(title = stringResource(R.string.search), canNavigateBack = true, navigateUp = navigateBack)}
    ){
        Column (modifier = Modifier.padding(it)){
            Text(text = stringResource(id = R.string.search))
        }
    }

}