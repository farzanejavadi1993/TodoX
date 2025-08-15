package com.fermer.task.local

import com.fermer.model.TaskModel


internal fun TaskEntity.toDomain() = TaskModel(
    id = id,
    title = title,
    isDone = isDone
)

internal fun TaskModel.toEntity() = TaskEntity(
    id = if (id.isBlank()) java.util.UUID.randomUUID().toString() else id,
    title = title,
    isDone = isDone
)