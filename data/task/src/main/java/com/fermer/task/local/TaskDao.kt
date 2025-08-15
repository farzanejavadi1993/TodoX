package com.fermer.task.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fermer.model.TaskModel
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getTasks(): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskEntity)

    @Query("DELETE FROM tasks WHERE id = :taskId")
    suspend fun delete(taskId: String)
}