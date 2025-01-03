package com.mada.todoapp.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mada.todoapp.data.data_source.database.TaskEntity
import com.mada.todoapp.domain.repo.TaskRepoImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val taskRepo: TaskRepoImpl) : ViewModel() {

    private val _tasks = MutableStateFlow<List<TaskEntity>>(emptyList())
    val tasks: StateFlow<List<TaskEntity>> = _tasks

    private val _task = MutableStateFlow<TaskEntity?>(null)  // StateFlow to hold a specific task
    val task: StateFlow<TaskEntity?> = _task
    init {
        getAllTasks() // تحميل المهام عند بداية إنشاء الـ ViewModel
    }

    fun getAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            _tasks.value = taskRepo.getAllTasks()
        }
    }
    fun getTaskById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val taskEntity = taskRepo.getTaskById(id)
            _task.value = taskEntity  // Update the StateFlow with the task value
        }
    }

    fun insertTask(taskEntity: TaskEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepo.insertTask(taskEntity)
            getAllTasks()
        }
    }

    fun deleteTask(taskEntity: TaskEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepo.deleteTask(taskEntity)
            getAllTasks()
        }
    }

    fun updateTask(taskEntity: TaskEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepo.updateTask(taskEntity)
            getAllTasks()
        }
    }
}
