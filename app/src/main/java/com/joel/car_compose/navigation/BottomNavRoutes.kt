package com.joel.car_compose.navigation

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavRoutes(
    val route : String,
    val icon: ImageVector,
    val title : String
){
    object Home: BottomNavRoutes(
        route = "home_route",
        icon = Icons.Default.Home,
        title = "Home"
    )
    object Profile: BottomNavRoutes(
        route = "profile_route",
        icon = Icons.Default.Person,
        title = "Profile"
    )
}
