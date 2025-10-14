package com.fermer.task.presentation

import com.fermer.common.mvi.UiEffect
import com.fermer.common.mvi.UiEvent
import com.fermer.common.mvi.UiState
import com.fermer.model.TaskModel

data class TaskListState(
    val tasks: List<TaskModel> = emptyList(),
    val isLoading: Boolean = false
) : UiState

sealed class TaskListEvent : UiEvent {
    data class AddTask(val title: String) : TaskListEvent()
    data class RemoveTask(val id: String) : TaskListEvent()
    data class ToggleTask(val task: TaskModel) : TaskListEvent()

    object LoadTasks : TaskListEvent()
}

sealed class TaskListEffect : UiEffect {
    data class ShowMessage(val message: String) : TaskListEffect()
}
