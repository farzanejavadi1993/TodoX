package com.fermer.model

import java.time.LocalDate

data class TaskModel(
    val id: String,
    val title: String,
    val isDone: Boolean,
    val dueDate: LocalDate ?,
    val priority: Priority
)
