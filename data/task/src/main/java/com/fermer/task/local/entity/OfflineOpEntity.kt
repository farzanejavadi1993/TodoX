package com.fermer.task.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "offline_ops")
data class OfflineOpEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val type: OpType,           // ADD or DELETE
    val taskId: String,
    val title: String?,
    val isDone: Boolean?,
    val createdAt: Long = System.currentTimeMillis(),
    val retryCount: Int = 0,
    val status: OpStatus = OpStatus.PENDING
)

enum class OpType { ADD, DELETE }
enum class OpStatus { PENDING, SENDING, DONE, FAILED }