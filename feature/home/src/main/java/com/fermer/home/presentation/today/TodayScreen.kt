package com.fermer.home.presentation.today

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.fermer.common.util.isToday
import com.fermer.task.presentation.TaskListEvent
import com.fermer.task.presentation.TaskListScreen
import com.fermer.task.presentation.TaskListViewModel
import com.fermer.task.presentation.components.TaskItem
@Composable
fun TodayScreen(
    viewModel: TodayViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()

    val filteredTasks =state.tasks

    TaskListScreen(
        title =  "Today's Tasks"  ,
        taskList = filteredTasks,
        onAddTask = { viewModel.sendEvent(TaskListEvent.AddTask(it)) },
        onRemoveTask = { viewModel.sendEvent(TaskListEvent.RemoveTask(it)) },
        onToggleCheck = { viewModel.sendEvent(TaskListEvent.ToggleTask(it)) }
    )
}

