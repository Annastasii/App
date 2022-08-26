package com.example.myapp.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapp.screens.first.StartScreen
import com.example.myapp.screens.info.InfoScreen
import com.example.myapp.screens.start.CountryScreen

@Composable
fun SetUpNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Start.route
    ) {
        composable(
            route = Screen.Country.route

        ) {
            CountryScreen(navController)
        }
        composable(
            route = "${Screen.Info.route}/{$COUNTRY}",
            arguments = listOf(navArgument(COUNTRY) { type = NavType.StringType })
        ) {
            InfoScreen(navController = navController, it.arguments?.getString(COUNTRY))
        }
        composable(
            route = Screen.Start.route
        ){
            StartScreen(navController)
        }
    }
}