package com.fermer.task.presentation

import androidx.lifecycle.viewModelScope
import com.fermer.common.mvi.MviViewModel
import com.fermer.domain.usecase.AddTaskUseCase
import com.fermer.domain.usecase.DeleteTaskUseCase
import com.fermer.domain.usecase.GetTasksUseCase
import com.fermer.domain.usecase.UpdateTaskUseCase
import com.fermer.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val getTaskUseCase: GetTasksUseCase
) : MviViewModel<TaskListState, TaskListEvent, TaskListEffect>(
    TaskListState(),
    TaskListReducer()
) {
    init {
        getTasks()
    }



    override fun handleEvent(event: TaskListEvent) {
        when (event) {
            is TaskListEvent.AddTask -> addTask(event.title)
            is TaskListEvent.RemoveTask -> removeTask(event.id)
            is TaskListEvent.ToggleTask -> updateTask(event.task)
            is TaskListEvent.LoadTasks -> getTasks()
        }
    }

    private fun getTasks() {
        getTaskUseCase()
            .onEach { updateState { it.copy(tasks = it.tasks, isLoading = false) } }
            .launchIn(viewModelScope)

    }

    private fun addTask(title: String) {
        viewModelScope.launch {
            addTaskUseCase(TaskModel(id = UUID.randomUUID().toString(), title = title, isDone = false))
            getTasks()
        }
    }


    private fun removeTask(id: String) {
        viewModelScope.launch {
            deleteTaskUseCase(id)
        }
    }

    private fun updateTask(task: TaskModel) {
        viewModelScope.launch {
            updateTaskUseCase(task)
        }
    }
}

