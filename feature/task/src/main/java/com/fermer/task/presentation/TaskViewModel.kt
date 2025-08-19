package com.fermer.task.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fermer.domain.GetTasksUseCase
import com.fermer.model.TaskModel
import com.fermer.task.domain.AddTaskUseCase
import com.fermer.task.domain.DeleteTaskUseCase
import com.fermer.task.domain.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
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
}