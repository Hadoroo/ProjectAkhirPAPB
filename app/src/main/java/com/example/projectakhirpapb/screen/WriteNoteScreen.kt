package com.example.projectakhirpapb.screen

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.projectakhirpapb.data.notes.Note
import com.example.projectakhirpapb.data.notes.NoteViewModel
import com.example.projectakhirpapb.data.notes.NotesViewModelFactory
import com.example.projectakhirpapb.navigation.Screen
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteNoteScreen(navController: NavHostController) {
    val application = LocalContext.current.applicationContext as Application
    val viewModel: NoteViewModel = viewModel(factory = NotesViewModelFactory(application))

    var title by remember { mutableStateOf(TextFieldValue("")) }
    var content by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Write Note") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (title.text.isNotBlank() && content.text.isNotBlank()) {
                        viewModel.insertNote(
                            Note(
                                id = UUID.randomUUID().toString(),
                                title = title.text,
                                note = content.text
                            )
                        )
                        navController.navigate(Screen.NotesScreen.route) {
                            popUpTo(Screen.NotesScreen.route) { inclusive = true }
                        }
                    }
                },
                modifier = Modifier.padding(bottom = 20.dp)
            ) {
                Icon(Icons.Default.Check, contentDescription = "Save Note")
            }

        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Content") },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}