package com.example.projectakhirpapb.data.todos

import com.google.firebase.firestore.DocumentId

data class ToDo(
    @DocumentId val id: String = "",
    val title: String = "",
    val description: String = "",
    val completed: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
