package com.fermer.task.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fermer.model.Priority
import java.time.LocalDate

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val id: String,
    val title: String,
    val isDone: Boolean,
    val dueDate: LocalDate?,
    val priority: Priority
)