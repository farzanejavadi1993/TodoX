package com.fermer.domain.usecase

import com.fermer.domain.task.TaskRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(taskId: String) {
        repository.removeTask(taskId)
    }
}