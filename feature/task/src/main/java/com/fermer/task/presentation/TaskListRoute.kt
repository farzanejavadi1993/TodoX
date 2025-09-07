package com.fermer.task.presentation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.work.WorkManager
import com.fermer.task.sync.SyncScheduler

@Composable
fun TaskListRoute(viewModel: TaskListViewModel = hiltViewModel()) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        scheduleSync(context = context)
    }

    val tasks by viewModel.tasks.collectAsState()

    TaskListScreen(
        taskList = tasks,
        onAddTask = { viewModel.addTask(it) },
        onRemoveTask = { viewModel.removeTask(it) },
        onToggleCheck = { viewModel.updateTask(it) }
    )
}

fun scheduleSync(context: Context) {
    SyncScheduler.enqueue(WorkManager.getInstance(context))
}
