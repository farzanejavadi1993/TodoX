package com.fermer.task.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [TaskEntity::class, OfflineOpEntity::class],
    version = 2,
    exportSchema = false
)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun offlineOpDao(): OfflineOpDao
}