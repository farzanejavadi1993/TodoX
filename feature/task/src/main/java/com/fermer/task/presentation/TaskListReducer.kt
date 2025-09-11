package com.fermer.task.presentation

import com.fermer.common.mvi.Reducer


class TaskListReducer : Reducer<TaskListState, TaskListEvent> {
    override fun reduce(
        currentState: TaskListState,
        event: TaskListEvent
    ): TaskListState {
        return when (event) {
            is TaskListEvent.LoadTasks -> currentState.copy(isLoading = true)
            else -> currentState // تغییر اصلی در ViewModel اعمال میشه
        }
    }
}
