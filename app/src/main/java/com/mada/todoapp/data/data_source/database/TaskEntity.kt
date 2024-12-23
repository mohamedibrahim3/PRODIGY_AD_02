package com.mada.todoapp.data.data_source.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val description: String
)
