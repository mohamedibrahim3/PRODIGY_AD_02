package com.mada.todoapp.presentation.screen

import android.R.attr.label
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mada.todoapp.data.data_source.database.TaskEntity
import com.mada.todoapp.presentation.viewmodel.TaskViewModel
import org.w3c.dom.Text


@Composable
fun DescriptionScreen(
    navController: NavController,
    taskViewModel: TaskViewModel,
    taskToEdit: TaskEntity? = null
) {
  var title by remember { mutableStateOf(taskToEdit?.title ?: "")}
  var description by remember { mutableStateOf(taskToEdit?.description ?: "") }

  Column(
      modifier = Modifier
          .fillMaxSize()
          .padding(8.dp)
  ) {

      TextField(
          value = title,
          onValueChange = { title = it },
          label = { Text(text = "Title") },
          modifier = Modifier.fillMaxWidth()
      )
      Spacer(modifier = Modifier.height(8.dp))

      TextField(
          value = description,
          onValueChange = { description = it },
          label = { Text(text = "Description") },
          modifier = Modifier.fillMaxWidth()
      )
      Spacer(modifier = Modifier.height(16.dp))
      Button(
          onClick = {
              if (title.isNotEmpty()) {
                  val task = TaskEntity(
                      id = taskToEdit?.id ?: 0, // If editing, use the existing ID
                      title = title,
                      description = description
                  )
                  if (taskToEdit == null) {
                      taskViewModel.insertTask(task) // Add new task
                  } else {
                      taskViewModel.updateTask(task) // Update existing task
                  }
                  navController.popBackStack() // Navigate back to the task list screen
              }
          },
          modifier = Modifier.fillMaxWidth()
      ) {
          Text(text = if (taskToEdit == null) "Add Task" else "Save Task")
      }

  }


}