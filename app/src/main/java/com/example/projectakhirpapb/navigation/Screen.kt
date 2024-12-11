package com.example.projectakhirpapb.navigation

sealed class Screen(val route: String) {
    object NotesScreen : Screen("Notes")
    object Login : Screen("login")
    object ProfileScreen : Screen("profile")
    object ToDoScreen : Screen("todo")
    object Register : Screen("register")
    object SettingsScreen : Screen("settings")
    object WriteNoteScreen : Screen("writeNote")

}
