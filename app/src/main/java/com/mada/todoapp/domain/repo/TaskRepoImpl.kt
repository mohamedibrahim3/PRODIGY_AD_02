package com.mada.todoapp.domain.repo

import com.mada.todoapp.data.data_source.database.TaskDao
import com.mada.todoapp.data.data_source.database.TaskEntity

class TaskRepoImpl(private val taskDao: TaskDao) : TaskDao {


    override suspend fun insertTask(task: TaskEntity): Long {
        return taskDao.insertTask(task)
    }

    override suspend fun updateTask(task: TaskEntity) {
        taskDao.updateTask(task)
    }

    override suspend fun deleteTask(task: TaskEntity) {
        taskDao.deleteTask(task)
    }

    override suspend fun getAllTasks(): List<TaskEntity> {
        return taskDao.getAllTasks()
    }

    override suspend fun getTaskById(id: Int): TaskEntity? {
        return taskDao.getTaskById(id)
    }
}