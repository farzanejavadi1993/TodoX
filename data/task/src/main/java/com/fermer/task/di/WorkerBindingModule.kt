package com.fermer.task.di

import com.fermer.task.sync.ChildWorkerFactory
import com.fermer.task.sync.SyncWorker
import com.fermer.task.sync.SyncWorkerFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap


@Module
@InstallIn(SingletonComponent::class)
abstract class WorkerBindingModule {

    @Binds
    @IntoMap
    @WorkerKey(SyncWorker::class)
    abstract fun bindSyncWorker(factory: SyncWorkerFactory): ChildWorkerFactory
}

