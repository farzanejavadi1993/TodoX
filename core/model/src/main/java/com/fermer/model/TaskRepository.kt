package com.fermer.model
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTasks(): Flow<List<TaskModel>>
    suspend fun addTask(task: TaskModel)
    suspend fun removeTask(id: String)
}