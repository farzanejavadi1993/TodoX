package com.fermer.task.presentation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.work.WorkManager
import com.fermer.common.util.isToday
import com.fermer.task.sync.SyncScheduler
/*


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
*/

@Composable
fun TaskListRoute(
    viewModel: TaskListViewModel = hiltViewModel(),
    isTodayTab: Boolean
) {
    val state by viewModel.uiState.collectAsState()

    val filteredTasks = remember(state.tasks, isTodayTab) {
        if (isTodayTab) {
            state.tasks.filter { it.dueDate!!.isToday() }
        } else {
            state.tasks.filter { it.dueDate!!.isToday() }
        }
    }

    TaskListScreen(
        title = if (isTodayTab) "Today's Tasks" else "Upcoming Tasks",
        taskList = filteredTasks,
        onAddTask = { viewModel.sendEvent(TaskListEvent.AddTask(it)) },
        onRemoveTask = { viewModel.sendEvent(TaskListEvent.RemoveTask(it)) },
        onToggleCheck = { viewModel.sendEvent(TaskListEvent.ToggleTask(it)) }
    )

}