package com.bpetel.meattracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bpetel.meattracker.navigation.AppNavHost
import com.bpetel.meattracker.navigation.BottomNavItem
import com.bpetel.meattracker.navigation.Screen
import com.bpetel.meattracker.ui.theme.MeatTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            val startDestination = Screen.Home

            MeatTrackerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        val items = listOf(
                            BottomNavItem.Home,
                            BottomNavItem.History
                        )
                        NavigationBar {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentRoute = navBackStackEntry?.destination?.route

                            items.forEach { item ->
                                val isSelected = currentRoute == item.screen::class.qualifiedName
                                NavigationBarItem(
                                    selected = isSelected,
                                    label = {
                                        Text(item.title)
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if (isSelected) item.filledIcon else item.outlinedIcon,
                                            contentDescription = item.title
                                        )
                                    },
                                    onClick = {
                                        navController.navigate(item.screen) {
                                            popUpTo(navController.graph.startDestinationId)
                                            launchSingleTop = true
                                        }
                                    }
                                )
                            }
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                navController.navigate(Screen.AddMeatEntry())
                            }
                        ) {
                            Icon(
                                Icons.Filled.Add,
                                "Floating action button."
                            )
                        }
                    }
                ) { innerPadding ->
                    AppNavHost(
                        navController,
                        startDestination,
                        Modifier.padding(innerPadding),
                        onEdit = {
                            navController.navigate(
                                Screen.AddMeatEntry(
                                    it.id,
                                    it.type,
                                    it.mealPart,
                                    it.weightInGrams.toString()
                                )
                            )
                        },
                        onSubmit = { navController.navigate(Screen.History) }
                    )
                }
            }
        }
    }
}