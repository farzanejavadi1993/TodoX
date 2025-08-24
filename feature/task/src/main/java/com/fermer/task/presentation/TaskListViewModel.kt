package com.fermer.task.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fermer.domain.usecase.AddTaskUseCase
import com.fermer.domain.usecase.DeleteTaskUseCase
import com.fermer.domain.usecase.GetTasksUseCase
import com.fermer.domain.usecase.UpdateTaskUseCase
import com.fermer.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val getTasksUseCase: GetTasksUseCase
) : ViewModel() {

    val tasks: StateFlow<List<TaskModel>> = getTasksUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addTask(title: String) {
        viewModelScope.launch {
            addTaskUseCase(TaskModel(id = "", title = title, isDone = false))
        }
    }

    fun removeTask(id: String) {
        viewModelScope.launch {
            deleteTaskUseCase(id)
        }

    }

    fun updateTask(task: TaskModel) {
        viewModelScope.launch {
            updateTaskUseCase(task)
        }
    }
}