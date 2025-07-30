package com.fermer.task.data

import com.fermer.model.TaskModel
import com.fermer.task.domain.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class FakeTaskRepository : TaskRepository {

    private val tasks = MutableStateFlow<List<TaskModel>>(emptyList())

    override fun getTasks(): Flow<List<TaskModel>> = tasks.asStateFlow()

    override suspend fun addTask(task: TaskModel) {
        tasks.value += task.copy(id = UUID.randomUUID().toString())
    }

    override suspend fun removeTask(taskId: String) {
        tasks.value = tasks.value.filterNot { it.id == taskId }
    }
}