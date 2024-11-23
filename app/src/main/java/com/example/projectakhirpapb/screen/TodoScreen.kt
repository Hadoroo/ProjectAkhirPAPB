package com.example.projectakhirpapb

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.material.icons.outlined.CheckBoxOutlineBlank
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectakhirpapb.data.local.ToDo
import com.example.projectakhirpapb.data.viewmodel.ToDoViewModel
import com.example.projectakhirpapb.data.viewmodel.ViewModelFactory

@Composable
fun ToDoScreen() {
    val application = LocalContext.current.applicationContext as Application
    val viewModel: ToDoViewModel = viewModel(factory = ViewModelFactory(application))

    val todos by viewModel.allToDos.collectAsState(initial = emptyList())
    var showDialog by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                modifier = Modifier.padding(bottom = 72.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add To-Do")
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = "To - Do List",
                fontSize = 24.sp,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )


            if (todos.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "Belum ada To-Do.",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 72.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(todos) { todo ->
                        ToDoItem(
                            todo = todo,
                            onDelete = { viewModel.deleteToDo(it) },
                            onToggleCompletion = { viewModel.toggleToDoCompletion(it) }
                        )
                    }
                }
            }

            if (showDialog) {
                AddToDoDialog(
                    title = title,
                    setTitle = { title = it },
                    description = description,
                    setDescription = { description = it },
                    onConfirm = {
                        if (title.text.isNotBlank() && description.text.isNotBlank()) {
                            viewModel.insertToDo(
                                ToDo(title = title.text, description = description.text)
                            )
                            title = TextFieldValue("")
                            description = TextFieldValue("")
                            showDialog = false
                        }
                    },
                    onDismiss = { showDialog = false }
                )
            }
        }
    }
}

@Composable
fun AddToDoDialog(
    title: TextFieldValue,
    setTitle: (TextFieldValue) -> Unit,
    description: TextFieldValue,
    setDescription: (TextFieldValue) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("New To-Do") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { setTitle(it) },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { setDescription(it) },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun ToDoItem(todo: ToDo, onDelete: (ToDo) -> Unit, onToggleCompletion: (ToDo) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (todo.isCompleted) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
            else MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = { onToggleCompletion(todo) }
                ) {
                    Icon(
                        imageVector = if (todo.isCompleted)
                            androidx.compose.material.icons.Icons.Outlined.CheckBox
                        else
                            androidx.compose.material.icons.Icons.Outlined.CheckBoxOutlineBlank,
                        contentDescription = "Toggle Completion",
                        tint = if (todo.isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(todo.title, style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(todo.description, style = MaterialTheme.typography.bodySmall)
                }
            }
            IconButton(onClick = { onDelete(todo) }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}
