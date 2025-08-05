package com.fermer.todox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fermer.task.presentation.TaskScreen
import com.fermer.task.presentation.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        enableEdgeToEdge()

        setContent {
            //val authViewModel: TaskViewModel = hiltViewModel()

            TaskScreen()


        }
    }
}
@Composable
fun TaskListRoute(viewModel: TaskViewModel = viewModel()) {
    val tasks by viewModel.tasks.collectAsState()

    /*TaskListScreen(
        taskList = tasks,
        onAddTaskClicked = {

        },
        onRemoveTask = { id -> viewModel.removeTask(id) }
    )*/
}

