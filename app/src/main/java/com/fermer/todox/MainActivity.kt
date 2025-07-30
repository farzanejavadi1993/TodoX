package com.fermer.todox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fermer.auth.presentation.AuthViewModel
import com.fermer.auth.presentation.screen.SignInScreen
import com.fermer.auth.presentation.screen.SignUpScreen
import com.fermer.task.presentation.TaskListScreen
import com.fermer.task.presentation.TaskViewModel
import com.fermer.todox.ui.theme.TodoXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        enableEdgeToEdge()

        setContent {
            val authViewModel: AuthViewModel = viewModel()

            SignInScreen  { email, password ->
                authViewModel.signIn(email, password)
            }


        }
    }
}
@Composable
fun TaskListRoute(viewModel: TaskViewModel = viewModel()) {
    val tasks by viewModel.tasks.collectAsState()

    TaskListScreen(
        taskList = tasks,
        onAddTaskClicked = {

        },
        onRemoveTask = { id -> viewModel.removeTask(id) }
    )
}

