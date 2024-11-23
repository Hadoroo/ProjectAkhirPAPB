package com.example.projectakhirpapb.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Define AuthState as a sealed class
sealed class AuthState {
    object Initial : AuthState()
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
}

class AuthViewModel : ViewModel() {
    private val auth = Firebase.auth
    private val _authState = MutableStateFlow<AuthState>(AuthState.Initial)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    init {
        // Check if user is already logged in
        auth.currentUser?.let {
            _authState.value = AuthState.Authenticated
        } ?: run {
            _authState.value = AuthState.Unauthenticated
        }
    }

    fun login(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            _authState.value = AuthState.Authenticated
                            onSuccess()
                        } else {
                            onError(task.exception?.message ?: "Login failed")
                        }
                    }
            } catch (e: Exception) {
                onError(e.message ?: "An error occurred")
            }
        }
    }

    fun signup(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            _authState.value = AuthState.Authenticated
                            onSuccess()
                        } else {
                            onError(task.exception?.message ?: "Signup failed")
                        }
                    }
            } catch (e: Exception) {
                onError(e.message ?: "An error occurred")
            }
        }
    }

    fun logout() {
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }
}
