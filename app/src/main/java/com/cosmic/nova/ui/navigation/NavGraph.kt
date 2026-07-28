package com.cosmic.nova.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cosmic.nova.ui.detail.DetailScreen
import com.cosmic.nova.ui.home.HomeScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{planetId}") {
        fun createRoute(planetId: String) = "detail/$planetId"
    }
}

@Composable
fun NovaNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onPlanetClick = { planetId ->
                    navController.navigate(Screen.Detail.createRoute(planetId))
                }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("planetId") { type = NavType.StringType })
        ) { backStackEntry ->
            val planetId = backStackEntry.arguments?.getString("planetId") ?: ""
            DetailScreen(
                planetId = planetId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
