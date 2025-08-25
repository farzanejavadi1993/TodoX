package com.fermer.task.di

import com.fermer.domain.task.TaskRepository
import com.fermer.domain.usecase.AddTaskUseCase
import com.fermer.domain.usecase.DeleteTaskUseCase
import com.fermer.domain.usecase.GetTasksUseCase
import com.fermer.domain.usecase.UpdateTaskUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object TaskModule {

    @Provides
    fun provideAddTaskUseCase(repository: TaskRepository) = AddTaskUseCase(repository)

    @Provides
    fun provideDeleteTaskUseCase(repository: TaskRepository) = DeleteTaskUseCase(repository)

    @Provides
    fun provideUpdateTaskUseCase(repository: TaskRepository) = UpdateTaskUseCase(repository)

    @Provides
    fun provideGetTasksUseCase(repository: TaskRepository) = GetTasksUseCase(repository)
}
