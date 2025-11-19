package com.bpetel.meattracker.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val screen: Screen,
    val title: String,
    val filledIcon: ImageVector,
    val outlinedIcon: ImageVector
) {
    object Home : BottomNavItem(
        Screen.Home,
        "Accueil",
        Icons.Filled.Home,
        Icons.Outlined.Home
    )
    object History : BottomNavItem(
        Screen.History,
        "Historique",
        Icons.AutoMirrored.Filled.List,
        Icons.AutoMirrored.Filled.List
    )
}