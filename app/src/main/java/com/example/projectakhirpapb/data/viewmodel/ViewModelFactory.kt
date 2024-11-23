package com.example.projectakhirpapb.data.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projectakhirpapb.data.local.ToDoDatabase
import com.example.projectakhirpapb.data.local.ToDoRepository

class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ToDoViewModel::class.java)) {
            val database = ToDoDatabase.getDatabase(application)
            val repository = ToDoRepository(database.toDoDao())
            return ToDoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
