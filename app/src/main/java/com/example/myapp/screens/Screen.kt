package com.example.myapp.screens

const val COUNTRY = "country"

sealed class Screen(val route: String) {
    object Start : Screen(route = "start_screen")
    object Info : Screen(route = "info_screen") {
    }
}