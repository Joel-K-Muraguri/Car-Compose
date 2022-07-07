package com.joel.car_compose.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun ContentScreen(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        BottomBarNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(
    navController: NavHostController
){
    val items = listOf(
        BottomNavRoutes.Home,
        BottomNavRoutes.Profile
    )

    BottomAppBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { route ->
            AddItems(
               screen = route,
               navController = navController,
               currentDestination = currentDestination
            )
        }
    }
}

@Composable
fun RowScope.AddItems(
    navController: NavHostController,
    screen : BottomNavRoutes,
    currentDestination : NavDestination?
){
    BottomNavigationItem(
        icon = {
               Icon(
                   imageVector = screen.icon,
                   contentDescription = screen.title )
        },
        label = {
                Text(text = screen.title)
        },
        selectedContentColor = Color.White,
        unselectedContentColor = Color.White.copy(0.4f),
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route){
                navController.graph.startDestinationRoute?.let { route->
                    popUpTo(route){
                        saveState = true
                    }
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ContentPreview(){
    ContentScreen()
}

