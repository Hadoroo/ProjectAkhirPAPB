package com.example.projectakhirpapb.data.notes

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class NoteRepository {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private val currentUserId: String
        get() = auth.currentUser?.uid ?: throw IllegalStateException("User not logged in")

    private val notesCollection get() = db.collection("users").document(currentUserId).collection("notes")

    suspend fun getAllNotes(): List<Note> {
        return try {
            val notes = notesCollection
                .orderBy("timestamp", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get()
                .await()
                .toObjects(Note::class.java)
            notes
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun insert(note: Note) {
        try {
            val noteWithTimestamp = note.copy(timestamp = System.currentTimeMillis())
            if (note.id.isEmpty()) {
                notesCollection.add(noteWithTimestamp).await()
            } else {
                notesCollection.document(note.id).set(noteWithTimestamp).await()
            }
        } catch (e: Exception) {

        }
    }


    suspend fun delete(note: Note) {
        try {
            notesCollection.document(note.id).delete().await()
        } catch (e: Exception) {

        }
    }
}