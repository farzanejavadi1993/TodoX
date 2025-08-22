package com.fermer.task.domain

import com.fermer.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(taskModel: TaskModel) {
        repository.updateTask(taskModel)
    }
}