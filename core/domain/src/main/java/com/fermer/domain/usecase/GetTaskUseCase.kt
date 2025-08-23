package com.fermer.domain.usecase

import com.fermer.domain.task.TaskRepository
import com.fermer.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    operator fun invoke(): Flow<List<TaskModel>> = repository.getTasks()
}