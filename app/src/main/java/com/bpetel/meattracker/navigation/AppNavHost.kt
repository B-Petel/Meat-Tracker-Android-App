package com.bpetel.meattracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bpetel.meattracker.presentation.HistoryScreen
import com.bpetel.meattracker.presentation.HomeScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: DestinationItem,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController,
        startDestination = startDestination.route,
        modifier
    ) {
        DestinationItem.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    DestinationItem.HOME -> HomeScreen()
                    DestinationItem.HISTORY -> HistoryScreen()
                }
            }
        }
    }
}