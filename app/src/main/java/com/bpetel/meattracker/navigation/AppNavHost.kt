package com.bpetel.meattracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bpetel.meattracker.presentation.AddMeatEntryScreen
import com.bpetel.meattracker.presentation.HistoryScreen
import com.bpetel.meattracker.presentation.HomeScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Screen,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController,
        startDestination = startDestination,
        modifier
    ) {
        composable<Screen.Home> { HomeScreen() }
        composable<Screen.History> { HistoryScreen() }
        composable<Screen.AddMeatEntry> { AddMeatEntryScreen() }
    }
}