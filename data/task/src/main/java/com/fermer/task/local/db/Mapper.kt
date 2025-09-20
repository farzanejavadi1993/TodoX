package com.fermer.task.local.db

import com.fermer.model.TaskModel
import com.fermer.task.local.entity.TaskEntity
import java.util.UUID

internal fun TaskEntity.toDomain() = TaskModel(
    id = id,
    title = title,
    isDone = isDone,
    dueDate = dueDate,
    priority = priority
)

internal fun TaskModel.toEntity() = TaskEntity(
    id = if (id.isBlank()) UUID.randomUUID().toString() else id,
    title = title,
    isDone = isDone,
    dueDate = dueDate,
    priority = priority
)