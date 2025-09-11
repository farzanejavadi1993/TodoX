package com.fermer.task.sync


import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.fermer.task.local.dao.OfflineOpDao
import com.fermer.task.local.entity.OpStatus
import com.fermer.task.local.entity.OpType
import com.google.firebase.firestore.FirebaseFirestore
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.tasks.await

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val offlineOpDao: OfflineOpDao,
    private val firestore: FirebaseFirestore
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        while (true) {
            val op = offlineOpDao.peekPending(OpStatus.PENDING) ?: break
            try {
                when (op.type) {
                    OpType.ADD -> {
                        val map = mapOf(
                            "id" to op.taskId,
                            "title" to (op.title ?: ""),
                            "isDone" to (op.isDone ?: false)
                        )
                        firestore.collection("tasks")
                            .document(op.taskId)
                            .set(map)
                            .await()
                    }
                    OpType.DELETE -> {
                        firestore.collection("tasks")
                            .document(op.taskId)
                            .delete()
                            .await()
                    }
                }
                offlineOpDao.setStatus(op.id, OpStatus.DONE)
            } catch (e: Exception) {
                offlineOpDao.incRetry(op.id)
                return Result.retry()
            }
        }
        return Result.success()
    }
}
