package com.fermer.task.local

import com.fermer.model.TaskModel
import com.fermer.task.local.dao.TaskDao
import com.fermer.task.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomTaskDataSource @Inject constructor(
    private val dao: TaskDao
) {

    fun getTasks(): Flow<List<TaskModel>> {
        return dao.getTasks().map { list ->
            list.map { TaskModel(it.id, it.title, it.isDone, it.dueDate, it.priority) }
        }
    }

    suspend fun addTask(task: TaskModel) {
        dao.insert(TaskEntity(task.id, task.title, task.isDone, task.dueDate, task.priority))
    }

    suspend fun removeTask(id: String) {
        dao.delete(id)
    }
}
