package com.revature.findpetsitter

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

sealed class Routes(val route: String) {

    object SplashScreen : Routes ("SplashScreen")
    object MainScreen : Routes ("MainScreen")
    object SignIn : Routes("SignIn")
    object CreateAccount : Routes("CreateAccount")
    object ListView: Routes("List")
    object AddPet: Routes("Addpet")
    object ProfileDetails: Routes("ProfileDetails")
    object ChooseService: Routes("Choose Service")
}

val Screens= listOf(
    Routes.ChooseService,
    Routes.AddPet
)

@Composable
fun RowScope.AddItem(
    screen:Routes,
    currentDestination: NavDestination?,
    navController: NavHostController
){
    BottomNavigationItem(
        label = { Text(text = screen.route) },
        icon = { Icon(imageVector = Icons.Default.Menu, contentDescription = "Navigation Icon" ) },
        selected = currentDestination?.hierarchy?.any { it.route == screen.route}==true,
        onClick = {
            navController.navigate(screen.route)
            {
                popUpTo(navController.graph.findStartDestination().id){ saveState=true }
                launchSingleTop=true
                restoreState=true
            }
        }

    )
}

@Composable
fun BottNavBar(navController:NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation() {
        Screens.forEach { screen ->

            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )

        }

    }

}