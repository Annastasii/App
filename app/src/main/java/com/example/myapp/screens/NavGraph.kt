package com.example.myapp.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapp.screens.info.InfoScreen
import com.example.myapp.screens.start.StartScreen

@Composable
fun SetUpNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Start.route
    ) {
        composable(
            route = Screen.Start.route

        ) {
            StartScreen(navController)
        }
        composable(
            route = "${Screen.Info.route}/{$COUNTRY}",
            arguments = listOf(navArgument(COUNTRY) { type = NavType.StringType })
        ) {
            InfoScreen(navController = navController, it.arguments?.getString(COUNTRY))
        }
    }
}