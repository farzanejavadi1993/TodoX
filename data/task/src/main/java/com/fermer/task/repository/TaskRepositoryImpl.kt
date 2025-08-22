package com.fermer.task.repository


import com.fermer.model.TaskModel
import com.fermer.task.domain.TaskRepository
import com.fermer.task.firebase.FirebaseTaskDataSource
import com.fermer.task.local.OfflineOpDao
import com.fermer.task.local.OfflineOpEntity
import com.fermer.task.local.OpStatus
import com.fermer.task.local.OpType
import com.fermer.task.local.RoomTaskDataSource
import com.fermer.task.local.TaskDao
import com.fermer.task.local.TaskEntity
import com.fermer.task.local.toDomain
import com.fermer.task.local.toEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject



class TaskRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val taskDao: TaskDao,
    private val offlineOpDao: OfflineOpDao,
    private val io: CoroutineDispatcher
) : TaskRepository {

   
    override fun getTasks(): Flow<List<TaskModel>> =
        taskDao.getTasks().map { list -> list.map { it.toDomain() } }


    override suspend fun addTask(task: TaskModel): Unit = withContext(io) {
        val entity = task.toEntity()
        try {

            taskDao.insert(entity)


            firestore.collection("tasks")
                .document(entity.id)
                .set(
                    mapOf(
                        "id" to entity.id,
                        "title" to entity.title,
                        "isDone" to entity.isDone
                    )
                )
                .await()
        } catch (e: Exception) {

            offlineOpDao.enqueue(
                OfflineOpEntity(
                    type = OpType.ADD,
                    taskId = entity.id,
                    title = entity.title,
                    isDone = entity.isDone,
                    status = OpStatus.PENDING
                )
            )
        }
    }
    override suspend fun removeTask(taskId: String): Unit = withContext(io) {
        try {

            taskDao.delete(taskId)


            firestore.collection("tasks")
                .document(taskId)
                .delete()
                .await()
        } catch (e: Exception) {

            offlineOpDao.enqueue(
                OfflineOpEntity(
                    type = OpType.DELETE,
                    taskId = taskId,
                    title = null,
                    isDone = null,
                    status = OpStatus.PENDING
                )
            )
        }
    }

    override suspend fun updateTask(task: TaskModel) {
        taskDao.update(task.toEntity())
    }
}