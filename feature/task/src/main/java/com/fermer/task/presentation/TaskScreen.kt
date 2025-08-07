package com.fermer.task.presentation


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fermer.task.presentation.components.TaskItem
/*

@Composable
fun TaskScreen(
    viewModel: TaskViewModel = hiltViewModel()
) {
    val tasks by viewModel.tasks.collectAsState()
    var newTaskTitle by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {

        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                label = { Text("New Task") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (newTaskTitle.isNotBlank()) {
                        viewModel.addTask(newTaskTitle)
                        newTaskTitle = ""
                    }
                }
            ) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(tasks) { task ->
                TaskItem(
                    task = task,
                    onDelete = { viewModel.removeTask(task.id) }
                )
            }
        }
    }
}
*/
