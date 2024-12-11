package com.example.projectakhirpapb.data.todos

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ToDoRepository {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private val currentUserId: String
        get() = auth.currentUser?.uid ?: throw IllegalStateException("User not logged in")

    private val todosCollection get() = db.collection("users").document(currentUserId).collection("todos")

    suspend fun getAllToDos(): List<ToDo> {
        return try {
            val todos = todosCollection
                .orderBy("createdAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get()
                .await()
                .toObjects(ToDo::class.java)
            todos
        } catch (e: Exception) {
            emptyList()
        }
    }


    suspend fun insert(todo: ToDo) {
        try {
            val todoToSave = if (todo.id.isEmpty()) {
                todo.copy(
                    createdAt = System.currentTimeMillis(),
                    updatedAt = System.currentTimeMillis()
                )
            } else {
                todo.copy(
                    updatedAt = System.currentTimeMillis()
                )
            }

            if (todo.id.isEmpty()) {
                todosCollection.add(todoToSave).await()
            } else {
                todosCollection.document(todo.id).set(todoToSave).await()
            }
        } catch (e: Exception) {
            // Handle error
        }
    }



    suspend fun delete(todo: ToDo) {
        try {
            todosCollection.document(todo.id).delete().await()
        } catch (e: Exception) {

        }
    }
}
