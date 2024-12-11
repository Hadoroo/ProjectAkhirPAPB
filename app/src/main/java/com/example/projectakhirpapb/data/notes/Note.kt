package com.example.projectakhirpapb.data.notes


import com.google.firebase.firestore.DocumentId

data class Note(
    @DocumentId val id: String = "",
    val title: String = "",
    val note: String = "",
    val timestamp: Long = System.currentTimeMillis()
)