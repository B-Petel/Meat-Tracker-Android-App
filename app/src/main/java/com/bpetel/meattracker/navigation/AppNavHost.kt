package com.bpetel.meattracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.bpetel.meattracker.domain.model.Meat
import com.bpetel.meattracker.presentation.history.HistoryScreen
import com.bpetel.meattracker.presentation.history.component.AddMeatEntryScreen
import com.bpetel.meattracker.presentation.home.HomeScreen
import com.bpetel.meattracker.presentation.stats.StatsScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Screen,
    modifier: Modifier = Modifier,
    onEdit: (Meat) -> Unit,
    onSubmit: () -> Unit
) {
    NavHost(
        navController,
        startDestination = startDestination,
        modifier
    ) {
        composable<Screen.Home> { HomeScreen() }
        composable<Screen.History> {
            HistoryScreen(
                onEdit = onEdit
            )
        }
        composable<Screen.AddMeatEntry> {
            val args = it.toRoute<Screen.AddMeatEntry>()

            AddMeatEntryScreen(
                args.id,
                args.type,
                args.parts,
                args.weight,
                args.timestamp,
                onSubmit = onSubmit
            )
        }
        composable<Screen.Stats> { StatsScreen() }
    }
}