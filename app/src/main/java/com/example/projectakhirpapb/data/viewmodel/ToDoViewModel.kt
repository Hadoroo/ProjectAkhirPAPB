package com.example.projectakhirpapb.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhirpapb.data.local.ToDo
import com.example.projectakhirpapb.data.local.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ToDoViewModel(private val repository: ToDoRepository) : ViewModel() {
    val allToDos: Flow<List<ToDo>> = repository.allToDos

    fun insertToDo(todo: ToDo) {
        viewModelScope.launch {
            repository.insert(todo)
        }
    }

    fun deleteToDo(todo: ToDo) {
        viewModelScope.launch {
            repository.delete(todo)
        }
    }

    fun toggleToDoCompletion(todo: ToDo) {
        viewModelScope.launch {
            val updatedToDo = todo.copy(isCompleted = !todo.isCompleted)
            repository.insert(updatedToDo)
        }
    }

}