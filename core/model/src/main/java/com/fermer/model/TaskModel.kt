package com.fermer.model

data class TaskModel(
    val id: String = "",
    val title: String = "",
    val isDone: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)