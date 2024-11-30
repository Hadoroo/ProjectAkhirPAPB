package com.example.projectakhirpapb.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object ProfileScreen : Screen("profile")
    object ToDoScreen : Screen("todo")
    object Register : Screen("register")
}
