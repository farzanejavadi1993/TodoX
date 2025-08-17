package com.fermer.task.di

import com.fermer.task.common.IoDispatcher
import com.fermer.task.domain.TaskRepository
import com.fermer.task.local.OfflineOpDao
import com.fermer.task.local.TaskDao
import com.fermer.task.repository.TaskRepositoryImpl
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TaskRepositoryModule {

    @Provides
    fun provideTaskRepository(
        firestore: FirebaseFirestore,
        taskDao: TaskDao,
        offlineOpDao: OfflineOpDao,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): TaskRepository = TaskRepositoryImpl(
        firestore,
        taskDao,
        offlineOpDao,
        ioDispatcher
    )
}