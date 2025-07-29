package com.fermer.tasklist.presentation.model

data class TaskListUiState(
    val tasks: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
