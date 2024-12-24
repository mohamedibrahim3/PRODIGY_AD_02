package com.mada.todoapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.mada.todoapp.data.data_source.database.TaskDatabase
import com.mada.todoapp.domain.repo.TaskRepoImpl
import com.mada.todoapp.presentation.screen.TodoListScreen
import com.mada.todoapp.presentation.viewmodel.TaskViewModel
import com.mada.todoapp.presentation.viewmodel.TaskViewModelFactory
import com.mada.todoapp.ui.theme.TodoAppTheme

class MainActivity : ComponentActivity() {
    private lateinit var taskViewModel: TaskViewModel

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the Room Database (TaskDatabase)
        val taskDatabase = TaskDatabase.getDatabase(applicationContext)
        // Initialize TaskRepoImpl with TaskDao from Room Database
        val taskRepo = TaskRepoImpl(taskDatabase.taskDao())

        // Initialize the ViewModel using the factory
        taskViewModel = ViewModelProvider(
            this,
            TaskViewModelFactory(taskRepo)  // Pass the TaskRepo to the factory
        ).get(TaskViewModel::class.java)

        setContent {
            TodoAppTheme {
                val navController = rememberNavController()  // Use navigation controller
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("Todo List") },
                            actions = {

                                IconButton(onClick = {

                                    println("Add Task clicked!")

                                }) {
                                    Icon(Icons.Filled.Add, contentDescription = "Add Task")
                                }
                            }
                        )
                    },
                    content = { paddingValues ->
                        TodoListScreen(
                            modifier = Modifier.padding(paddingValues),
                            taskViewModel = taskViewModel,
                            onTaskClick = { task ->

                            },
                            onAddClick = {
                                // Handle the add button click

                                println("Add task clicked from TodoListScreen")
                            }
                        )
                    }
                )
            }
        }
    }
}

