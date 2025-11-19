package com.bpetel.meattracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.bpetel.meattracker.navigation.AppNavHost
import com.bpetel.meattracker.navigation.DestinationItem
import com.bpetel.meattracker.ui.theme.MeatTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            val navController = rememberNavController()
            val startDestination = DestinationItem.HOME
            var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

            MeatTrackerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            DestinationItem.entries.forEachIndexed { index, destination ->
                                NavigationBarItem(
                                    selected = selectedDestination == index,
                                    onClick = {
                                        navController.navigate(route = destination.route)
                                        selectedDestination = index
                                    },
                                    icon = {
                                        Icon(
                                            if (index == selectedDestination) {
                                                destination.iconFilled
                                            } else destination.iconOutlined,
                                            contentDescription = ""
                                        )
                                    },
                                    label = { Text(destination.label) }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    AppNavHost(
                        navController,
                        startDestination,
                        Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}