package com.fermer.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.fermer.domain.task.TaskRepository
import com.fermer.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class GetTodayTasksUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke(): Flow<List<TaskModel>> {
        val today = LocalDate.now()
        return repository.getTasks().map { tasks ->
            tasks.filter { it.dueDate == today }
        }
    }
}
