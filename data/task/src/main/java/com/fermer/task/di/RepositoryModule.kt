package com.fermer.task.di

import com.fermer.model.TaskRepository
import com.fermer.task.firebase.FirebaseTaskDataSource
import com.fermer.task.repository.TaskRepositoryImpl
import dagger.Provides
import javax.inject.Singleton

@Provides
@Singleton
fun provideTaskRepository(
    firebase: FirebaseTaskDataSource,
    //room: RoomTaskDataSource
): TaskRepository {
    return TaskRepositoryImpl(firebase )
}