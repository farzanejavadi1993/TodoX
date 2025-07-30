package com.fermer.task.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fermer.model.TaskModel
import com.fermer.task.domain.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    val tasks: StateFlow<List<TaskModel>> =
        repository.getTasks()
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addTask(title: String) {
        viewModelScope.launch {
            repository.addTask(TaskModel(title = title))
        }
    }

    fun removeTask(id: String) {
        viewModelScope.launch {
            repository.removeTask(id)
        }
    }
}