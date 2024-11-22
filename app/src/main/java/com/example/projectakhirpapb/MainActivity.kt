package com.example.projectakhirpapb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.projectakhirpapb.ui.theme.ProjectAkhirPAPBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KeepApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeepApp() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Search your notes") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle menu click */ }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle add click */ }) {
                        Icon(Icons.Filled.Add, contentDescription = "Add")
                    }
                },
                backgroundColor = Color.Black,
                contentColor = Color.White
            )
        },
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.Black,
                contentColor = Color.White,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Add bottom navigation items here
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Menu, contentDescription = "Menu") },
                    selected = false,
                    onClick = { /* Handle bottom nav click */ }
                )
            }
        },
        content = { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                modifier = Modifier.fillMaxSize()
            ) {
                items(listOf("Note 1", "Note 2", "Note 3")) { note ->
                    Text(
                        text = note,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable { /* Handle note click */ }
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun KeepAppPreview() {
    KeepApp()
}
