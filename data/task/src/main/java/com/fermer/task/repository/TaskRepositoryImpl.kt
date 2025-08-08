package com.fermer.task.repository


import com.fermer.model.TaskModel
import com.fermer.task.domain.TaskRepository
import com.fermer.task.firebase.FirebaseTaskDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val firebase: FirebaseTaskDataSource,
    //private val room: RoomTaskDataSource
) : TaskRepository {

    override fun getTasks(): Flow<List<TaskModel>> {
        return firebase.getTasks()
    }

    override suspend fun addTask(task: TaskModel) {
        firebase.addTask(task)
    }

    override suspend fun removeTask(id: String) {
        firebase.removeTask(id)
    }
}
