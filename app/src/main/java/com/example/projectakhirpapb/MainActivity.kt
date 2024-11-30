package com.example.projectakhirpapb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.example.projectakhirpapb.screen.RegisterScreen
import com.example.projectakhirpapb.ui.theme.ProjectAkhirPAPBTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.FirebaseApp


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase App
        FirebaseApp.initializeApp(this)

        setContent {
            ProjectAkhirPAPBTheme {
                val navController = rememberNavController()
                val auth = FirebaseAuth.getInstance()

                if (auth.currentUser == null) {
                    // Tampilkan tampilan login jika pengguna belum login
                    LoginScreen(auth = auth, navController = navController)
                } else {
                    MainScreen(auth = auth, navController = navController)
                }
            }
        }
    }
}

@Composable
fun MainScreen(auth: FirebaseAuth, navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { innerPadding ->
        NavigationGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            auth = auth
        )
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    auth: FirebaseAuth
) {
    val startDestination = if (auth.currentUser != null) Screen.Home.route else Screen.Login.route

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Login.route) {
            LoginScreen(auth = auth, navController = navController)
        }
        composable(Screen.Home.route) {
            HomeScreen()
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
    }
}




@Composable
fun BottomBar(navController: NavHostController) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Homepage") },
            label = { Text("Notes") },
            selected = backStackEntry.value?.destination?.route == Screen.Home.route,
            onClick = {
                navController.navigate(Screen.Home.route) {
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
            icon = { Icon(Icons.Default.List, contentDescription = "Profile") },
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

@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Text(
            text = "Home Screen",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )
    }
}
