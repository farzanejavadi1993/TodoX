package com.fermer.task.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fermer.common.mvi.MviViewModel
import com.fermer.domain.usecase.AddTaskUseCase
import com.fermer.domain.usecase.DeleteTaskUseCase
import com.fermer.domain.usecase.GetTasksUseCase
import com.fermer.domain.usecase.UpdateTaskUseCase
import com.fermer.model.Priority
import com.fermer.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
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
        updateState { it.copy(isLoading = true) }

        getTaskUseCase()
            .onEach { taskList ->
                updateState {
                    it.copy(
                        tasks = taskList,
                        isLoading = false
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun addTask(title: String) {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true) }

            addTaskUseCase(
                TaskModel(
                    id = UUID.randomUUID().toString(),
                    title = title,
                    isDone = false,
                    dueDate = LocalDate.now(),
                    priority = Priority.LOW
                )
            )

            getTasks()
        }
    }

    private fun removeTask(id: String) {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true) }

            deleteTaskUseCase(id)

            getTasks()
        }
    }

    private fun updateTask(task: TaskModel) {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true) }

            updateTaskUseCase(task)

            getTasks()
        }
    }
}