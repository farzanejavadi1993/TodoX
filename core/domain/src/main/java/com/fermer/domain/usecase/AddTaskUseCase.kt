package com.fermer.domain.usecase

import com.fermer.domain.task.TaskRepository
import com.fermer.model.TaskModel
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: TaskModel) {
        repository.addTask(task)
    }
}
