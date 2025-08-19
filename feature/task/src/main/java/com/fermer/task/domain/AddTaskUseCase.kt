package com.fermer.task.domain

import com.fermer.model.TaskModel
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: TaskModel) {
        repository.addTask(task)
    }
}
