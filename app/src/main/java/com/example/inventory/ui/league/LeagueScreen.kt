package com.example.inventory.ui.league

import com.example.inventory.R
import com.example.inventory.ui.navigation.NavigationDestination

object LeagueDestination : NavigationDestination {
    override val route = "league"
    override val titleRes = R.string.app_name
}

//@OptIn(ExperimentalMaterial3Api::class)
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun LeagueScreen(
//    navigateToItemEntry: () -> Unit,
//    navigateToItemEdit: (Long) -> Unit,
//    viewModel: LeagueViewModel = viewModel(factory = AppViewModelProvider.Factory)
//){
//
//}