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
fun TaskListRoute(
    viewModel: TaskListViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        scheduleSync(context = context)
    }

    TaskListScreen(
        taskList = state.tasks,
        onAddTask = { viewModel.sendEvent(TaskListEvent.AddTask(it)) },
        onRemoveTask = { viewModel.sendEvent(TaskListEvent.RemoveTask(it)) },
        onToggleCheck = { viewModel.sendEvent(TaskListEvent.ToggleTask(it)) }
    )
}
fun scheduleSync(context: Context) {
    SyncScheduler.enqueue(WorkManager.getInstance(context))
}
