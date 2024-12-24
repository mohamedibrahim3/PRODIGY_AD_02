package com.mada.todoapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mada.todoapp.domain.repo.TaskRepoImpl

class TaskViewModelFactory(private val taskRepo: TaskRepoImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(taskRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
