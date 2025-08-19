package com.fermer.domain

import com.fermer.model.TaskModel
import com.fermer.task.domain.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    operator fun invoke(): Flow<List<TaskModel>> = repository.getTasks()
}
