package com.fermer.task.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fermer.task.local.entity.OfflineOpEntity
import com.fermer.task.local.entity.OpStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface OfflineOpDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun enqueue(op: OfflineOpEntity)

    @Query("SELECT * FROM offline_ops WHERE status = :status ORDER BY createdAt ASC")
    fun observeByStatus(status: OpStatus = OpStatus.PENDING): Flow<List<OfflineOpEntity>>

    @Query("SELECT * FROM offline_ops WHERE status = :status ORDER BY createdAt ASC LIMIT 1")
    suspend fun peekPending(status: OpStatus = OpStatus.PENDING): OfflineOpEntity?

    @Update
    suspend fun update(op: OfflineOpEntity)

    @Query("UPDATE offline_ops SET status = :status WHERE id = :id")
    suspend fun setStatus(id: Long, status: OpStatus)

    @Query("UPDATE offline_ops SET retryCount = retryCount + 1 WHERE id = :id")
    suspend fun incRetry(id: Long)

    @Query("DELETE FROM offline_ops WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("DELETE FROM offline_ops WHERE status = :status")
    suspend fun clearByStatus(status: OpStatus = OpStatus.DONE)
}

