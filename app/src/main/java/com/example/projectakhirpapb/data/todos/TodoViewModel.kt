package com.example.projectakhirpapb.data.todos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ToDoViewModel(private val repository: ToDoRepository) : ViewModel() {

    private val _allToDos = MutableStateFlow<List<ToDo>>(emptyList())
    val allToDos: StateFlow<List<ToDo>> get() = _allToDos

    init {
        loadToDos()
    }

    private fun loadToDos() {
        viewModelScope.launch {
            _allToDos.value = repository.getAllToDos()
        }
    }

    fun insertToDo(todo: ToDo) {
        viewModelScope.launch {
            repository.insert(todo)
            loadToDos()
        }
    }


    fun deleteToDo(todo: ToDo) {
        viewModelScope.launch {
            repository.delete(todo)
            loadToDos()
        }
    }

    fun toggleToDoCompletion(todo: ToDo) {
        val updatedToDo = todo.copy(
            completed = !todo.completed
        )
        viewModelScope.launch {
            repository.insert(updatedToDo)
            loadToDos()
        }
    }

}