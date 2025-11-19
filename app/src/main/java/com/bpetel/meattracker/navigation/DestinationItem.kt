package com.bpetel.meattracker.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class DestinationItem (
    val route: String,
    val label: String,
    val iconFilled: ImageVector,
    val iconOutlined: ImageVector
) {
    HOME("home", "Home", Icons.Filled.Home, Icons.Outlined.Home),
    HISTORY("history", "History", Icons.AutoMirrored.Filled.List, Icons.AutoMirrored.Outlined.List)
}