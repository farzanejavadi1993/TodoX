package com.fermer.domain.task

import com.fermer.model.TaskModel
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTasks(): Flow<List<TaskModel>>
    suspend fun addTask(task: TaskModel)
    suspend fun removeTask(taskId: String)
    suspend fun updateTask(task: TaskModel)


}