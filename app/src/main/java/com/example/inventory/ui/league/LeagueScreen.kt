package com.example.inventory.ui.league

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.inventory.R
import com.example.inventory.ui.AppViewModelProvider
import com.example.inventory.ui.league.LeagueDestination.leagueId
import com.example.inventory.ui.navigation.NavigationDestination

object LeagueDestination : NavigationDestination {
    override val route = "league"
    val routeWithArgs = "$route/{leagueId}"
    override val titleRes = R.string.app_name
    const val leagueId = "leagueId"
}

@Composable
fun LeagueScreen(
    navigateToItemEdit: (Long) -> Unit,
    viewModel: LeagueViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavController // Inject NavController
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val arguments = navBackStackEntry?.arguments
    val leagueIdArg = arguments?.getString(leagueId)
    Text(text = "Details for league with ID $leagueIdArg")

}