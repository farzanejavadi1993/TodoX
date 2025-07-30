package com.fermer.task.di

import com.fermer.task.data.FakeTaskRepository
import com.fermer.task.domain.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TaskModule {

    @Binds
    @Singleton
    abstract fun bindTaskRepository(
        fakeTaskRepository: FakeTaskRepository
    ): TaskRepository
}