package com.fermer.home.presentation.upcoming

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.fermer.common.util.isToday
import com.fermer.home.presentation.today.TodayViewModel
import com.fermer.task.presentation.TaskListEvent
import com.fermer.task.presentation.TaskListScreen

@Composable
fun UpcomingScreen(
    viewModel: UpcomingViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()

    val filteredTasks = state.tasks

    TaskListScreen(
        title =  "Upcoming Tasks",
        taskList = filteredTasks,
        onAddTask = { viewModel.sendEvent(TaskListEvent.AddTask(it)) },
        onRemoveTask = { viewModel.sendEvent(TaskListEvent.RemoveTask(it)) },
        onToggleCheck = { viewModel.sendEvent(TaskListEvent.ToggleTask(it)) }
    )
}
