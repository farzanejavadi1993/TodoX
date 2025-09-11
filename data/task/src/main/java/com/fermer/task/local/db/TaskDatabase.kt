package com.fermer.task.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fermer.task.local.dao.OfflineOpDao
import com.fermer.task.local.dao.TaskDao
import com.fermer.task.local.entity.OfflineOpEntity
import com.fermer.task.local.entity.TaskEntity


@Database(
    entities = [TaskEntity::class, OfflineOpEntity::class],
    version = 2,
    exportSchema = false
)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun offlineOpDao(): OfflineOpDao
}