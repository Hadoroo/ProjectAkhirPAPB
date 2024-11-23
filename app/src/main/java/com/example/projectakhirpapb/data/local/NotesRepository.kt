package com.example.projectakhirpapb.data.local

import kotlinx.coroutines.flow.Flow

class ToDoRepository(private val toDoDao: ToDoDao) {
    val allToDos: Flow<List<ToDo>> = toDoDao.getAllToDos()

    suspend fun insert(toDo: ToDo) {
        toDoDao.insertToDo(toDo)
    }

    suspend fun delete(toDo: ToDo) {
        toDoDao.deleteToDo(toDo)
    }
}