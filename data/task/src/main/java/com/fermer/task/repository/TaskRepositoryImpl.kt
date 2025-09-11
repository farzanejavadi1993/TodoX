package com.fermer.task.repository


import com.fermer.domain.task.TaskRepository
import com.fermer.model.TaskModel
import com.fermer.task.local.dao.OfflineOpDao
import com.fermer.task.local.entity.OfflineOpEntity
import com.fermer.task.local.entity.OpStatus
import com.fermer.task.local.entity.OpType
import com.fermer.task.local.dao.TaskDao
import com.fermer.task.local.db.toDomain
import com.fermer.task.local.db.toEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


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