package com.joel.car_compose.navigation

import android.content.Context
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.joel.car_compose.ui.cars.CarSharedViewModel

@Composable
fun ContentScreen(
    carSharedViewModel: CarSharedViewModel,
    context: Context
){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)

        }
    ) {
        BottomBarNavGraph(navController = navController, carSharedViewModel, context )
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

    BottomAppBar(
        elevation = 10.dp,
        backgroundColor = Color.White
    ) {
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

        selectedContentColor = Color.Black,
        unselectedContentColor = Color.Black.copy(0.4f),
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

/*@Preview(showBackground = true)
@Composable
fun ContentPreview(){
    ContentScreen()
}*/

