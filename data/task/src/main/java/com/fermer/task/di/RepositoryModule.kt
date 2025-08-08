package com.fermer.task.di

import com.fermer.task.domain.TaskRepository
import com.fermer.task.firebase.FirebaseTaskDataSource
import com.fermer.task.repository.TaskRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
@Provides
@Singleton
fun provideTaskRepository(
    firebase: FirebaseTaskDataSource,
    //room: RoomTaskDataSource
): TaskRepository {
    return TaskRepositoryImpl(firebase )
}
    }