package com.fermer.task.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TaskListRoute(viewModel: TaskViewModel = hiltViewModel()) {

    val tasks by viewModel.tasks.collectAsState()

    TaskListScreen(
        taskList = tasks,
        onAddTask = { viewModel.addTask(it) },
        onRemoveTask = { viewModel.removeTask(it) }
    )
}
