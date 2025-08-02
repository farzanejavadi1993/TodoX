package com.fermer.task.firebase

import com.fermer.model.TaskModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseTaskDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    private val tasksCollection = firestore.collection("tasks")

    fun getTasks(): Flow<List<TaskModel>> = callbackFlow {
        val listener = tasksCollection.addSnapshotListener { snapshot, _ ->
            val tasks = snapshot?.documents?.mapNotNull { it.toObject<TaskModel>() } ?: emptyList()
            trySend(tasks)
        }
        awaitClose { listener.remove() }
    }

    suspend fun addTask(task: TaskModel) {
        tasksCollection.document(task.id).set(task).await()
    }

    suspend fun removeTask(id: String) {
        tasksCollection.document(id).delete().await()
    }
}
