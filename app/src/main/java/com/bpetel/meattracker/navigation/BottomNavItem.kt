package com.bpetel.meattracker.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val screen: Screen,
    val title: String,
    val filledIcon: ImageVector,
    val outlinedIcon: ImageVector
) {
    object Home: BottomNavItem(
        Screen.Home,
        "Accueil",
        Icons.Filled.Home,
        Icons.Outlined.Home
    )
    object History: BottomNavItem(
        Screen.History,
        "Historique",
        Icons.AutoMirrored.Filled.Assignment,
        Icons.AutoMirrored.Outlined.Assignment
    )
    object Stats: BottomNavItem(
        Screen.Stats,
        "Statistique",
        Icons.Filled.Analytics,
        Icons.Outlined.Analytics
    )
}