package com.example.projectakhirpapb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.projectakhirpapb.navigation.Screen
import com.example.projectakhirpapb.screen.ProfileScreen
import com.example.projectakhirpapb.screen.LoginScreen
import com.example.projectakhirpapb.screen.NotesScreen
import com.example.projectakhirpapb.screen.RegisterScreen
import com.example.projectakhirpapb.screen.SettingsScreen
import com.example.projectakhirpapb.screen.WriteNoteScreen
import com.example.projectakhirpapb.ui.theme.ProjectAkhirPAPBTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.auth


class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(this)

        setContent {
            ProjectAkhirPAPBTheme {
                val navController = rememberNavController()
                val isUserLoggedIn = remember { mutableStateOf(auth.currentUser != null) }

                val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
                    val user = firebaseAuth.currentUser
                    isUserLoggedIn.value = user != null
                }

                auth.addAuthStateListener(authStateListener)

                Scaffold(
                    bottomBar = {

                        if (isUserLoggedIn.value) {
                            BottomBar(navController = navController)
                        }
                    }
                ) { paddingValues ->
                    NavigationGraph(
                        navController = navController,
                        modifier = Modifier.padding(paddingValues),
                        auth = auth
                    )
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()

        auth.removeAuthStateListener { firebaseAuth -> }
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    auth: FirebaseAuth
) {
    val startDestination = if (auth.currentUser != null) Screen.NotesScreen.route else Screen.Login.route

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.NotesScreen.route) {
            NotesScreen(navController = navController)
        }

        composable(Screen.Login.route) {
            LoginScreen(auth = auth, navController = navController)
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController, auth = auth)
        }
        composable(Screen.Register.route) {
            RegisterScreen(auth = auth, navController = navController)
        }
        composable(Screen.ToDoScreen.route) {
            ToDoScreen()
        }
        composable(Screen.SettingsScreen.route) {
            SettingsScreen(navController = navController, auth = auth)
        }
        composable(Screen.WriteNoteScreen.route) {
            WriteNoteScreen(navController = navController)
        }

    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Notes") },
            label = { Text("Notes") },
            selected = backStackEntry.value?.destination?.route == Screen.NotesScreen.route,
            onClick = {
                navController.navigate(Screen.NotesScreen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    restoreState = true
                    launchSingleTop = true
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "To-Do") },
            label = { Text("To-Do") },
            selected = backStackEntry.value?.destination?.route == Screen.ToDoScreen.route,
            onClick = {
                navController.navigate(Screen.ToDoScreen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    restoreState = true
                    launchSingleTop = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = backStackEntry.value?.destination?.route == Screen.ProfileScreen.route,
            onClick = {
                navController.navigate(Screen.ProfileScreen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    restoreState = true
                    launchSingleTop = true
                }
            }
        )
    }
}
