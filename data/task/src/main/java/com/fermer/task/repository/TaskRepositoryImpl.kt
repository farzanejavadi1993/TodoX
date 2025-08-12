package com.fermer.task.repository


import com.fermer.model.TaskModel
import com.fermer.task.domain.TaskRepository
import com.fermer.task.firebase.FirebaseTaskDataSource
import com.fermer.task.local.RoomTaskDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class TaskRepositoryImpl @Inject constructor(
    private val firebase: FirebaseTaskDataSource,
    private val room: RoomTaskDataSource
) : TaskRepository {

    override fun getTasks(): Flow<List<TaskModel>> = room.getTasks()

    override suspend fun addTask(task: TaskModel) {
        room.addTask(task)
        runCatching { firebase.addTask(task) }
    }

    override suspend fun removeTask(id: String) {
        room.removeTask(id)
        runCatching { firebase.removeTask(id) }
    }
}
