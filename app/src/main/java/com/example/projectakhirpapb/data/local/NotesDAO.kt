package com.example.projectakhirpapb.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(todo: ToDo)

    @Query("SELECT * FROM todo_table ORDER BY id DESC")
    fun getAllToDos(): Flow<List<ToDo>>

    @Delete
    suspend fun deleteToDo(todo: ToDo)
}
