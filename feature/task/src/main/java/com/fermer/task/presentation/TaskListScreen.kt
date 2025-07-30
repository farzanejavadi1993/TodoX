package com.fermer.task.presentation


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fermer.model.TaskModel

@Composable
fun TaskListScreen(
    taskList: List<TaskModel>,
    onAddTaskClicked: () -> Unit,
    onRemoveTask: (String) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddTaskClicked) {
                Text("+")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (taskList.isEmpty()) {
                Text("No tasks yet")
            } else {
                taskList.forEach { task ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable { onRemoveTask(task.id) }
                    ) {
                        Text(
                            text = task.title,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = if (task.isDone) "✅" else "❌"
                        )
                    }
                }
            }
        }
    }
}
