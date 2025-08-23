package com.fermer.domain.usecase

import com.fermer.domain.task.TaskRepository
import com.fermer.model.TaskModel
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(taskModel: TaskModel) {
        repository.updateTask(taskModel)
    }
}