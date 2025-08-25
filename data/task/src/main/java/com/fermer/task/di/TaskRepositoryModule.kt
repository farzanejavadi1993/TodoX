package com.fermer.task.di

import com.fermer.common.IoDispatcher
import com.fermer.domain.task.TaskRepository
import com.fermer.task.local.OfflineOpDao
import com.fermer.task.local.TaskDao
import com.fermer.task.repository.TaskRepositoryImpl
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object TaskRepositoryModule {

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