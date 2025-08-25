package com.fermer.tasklist.presentation
/*


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fermer.tasklist.presentation.model.TaskListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TaskListUiState())
    val uiState: StateFlow<TaskListUiState> = _uiState

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {

                kotlinx.coroutines.delay(1000)

                val fakeTasks = listOf("Buy groceries", "Call Mom", "Fix bug #13")
                _uiState.value = _uiState.value.copy(
                    tasks = fakeTasks,
                    isLoading = false
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}
*/
