package com.mada.todoapp.data.data_source.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {


    @Insert
    suspend fun insertTask(task: TaskEntity):Long

    @Insert
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Query("SELECT * FROM Tasks")
    suspend fun getAllTasks(): List<TaskEntity>

    @Query("SELECT * FROM Tasks WHERE id = :id LIMIT 1")
    suspend fun getTaskById(id: Int): TaskEntity?
}
