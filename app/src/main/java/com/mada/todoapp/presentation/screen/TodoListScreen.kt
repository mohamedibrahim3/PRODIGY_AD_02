package com.mada.todoapp.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mada.todoapp.data.data_source.database.TaskEntity
import com.mada.todoapp.presentation.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel,
    onTaskClick: (TaskEntity) -> Unit,
    onAddClick: () -> Unit  // Accepting onAddClick as a parameter
) {
    val tasks by taskViewModel.tasks.collectAsState()

    LazyColumn(
        modifier = Modifier.padding(8.dp) // Apply padding here
    ) {
        items(tasks) { task ->
            TaskItem(task = task, onTaskClick = onTaskClick, taskViewModel)
        }
    }
}


@Composable
fun TaskItem(
    task: TaskEntity,
    onTaskClick: (TaskEntity) -> Unit,
    taskViewModel: TaskViewModel
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onTaskClick(task) }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    fontSize = 22.sp
                )
                Text(
                    text = task.description,
                    fontSize = 16.sp
                )
            }
            IconButton(onClick = { taskViewModel.deleteTask(task) }) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete Task")
            }
        }
    }

}
