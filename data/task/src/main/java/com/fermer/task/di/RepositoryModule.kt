package com.fermer.task.di

import com.fermer.task.domain.TaskRepository
import com.fermer.task.firebase.FirebaseTaskDataSource
import com.fermer.task.repository.TaskRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {


    @Provides
    @ViewModelScoped
    fun provideTaskRepository(
        firebase: FirebaseTaskDataSource,
        //room: RoomTaskDataSource
    ): TaskRepository {
        return TaskRepositoryImpl(firebase)
    }


}