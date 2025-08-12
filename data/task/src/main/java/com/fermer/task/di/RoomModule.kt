package com.fermer.task.di

import android.content.Context
import androidx.room.Room
import com.fermer.task.firebase.FirebaseTaskDataSource
import com.fermer.task.local.RoomTaskDataSource
import com.fermer.task.local.TaskDao
import com.fermer.task.local.TaskDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TaskDataModule {

    @Provides @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TaskDatabase =
        Room.databaseBuilder(context, TaskDatabase::class.java, "task_db").build()

    @Provides
    fun provideTaskDao(db: TaskDatabase): TaskDao = db.taskDao()

    @Provides @Singleton
    fun provideFirebaseTaskDataSource(
        firestore: FirebaseFirestore
    ): FirebaseTaskDataSource = FirebaseTaskDataSource(firestore)

    @Provides @Singleton
    fun provideRoomTaskDataSource(
        dao: TaskDao
    ): RoomTaskDataSource = RoomTaskDataSource(dao)
}