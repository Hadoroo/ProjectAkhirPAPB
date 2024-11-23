package com.example.projectakhirpapb.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object ToDoScreen : Screen("todo")
}