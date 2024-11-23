package com.example.projectakhirpapb.data.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {
    private val _screenTitle = MutableStateFlow("To-Do List")
    val screenTitle: StateFlow<String> get() = _screenTitle

    fun updateScreenTitle(title: String) {
        _screenTitle.value = title
    }
}