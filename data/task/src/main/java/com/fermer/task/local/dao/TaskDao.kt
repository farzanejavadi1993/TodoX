package com.fermer.task.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fermer.task.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getTasks(): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskEntity)

    @Query("DELETE FROM tasks WHERE id = :taskId")
    suspend fun delete(taskId: String)

    @Update
    suspend fun update(task: TaskEntity)
}