package com.fermer.task.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fermer.model.TaskModel
import com.fermer.task.presentation.components.TaskItem

@Composable
fun TaskListScreen(
    taskList: List<TaskModel>,
    onAddTask: (String) -> Unit,
    onRemoveTask: (String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var newTaskTitle by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
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
                LazyColumn {
                    items(taskList, key = { it.id }) { task ->
                        TaskItem(
                            task = task,
                            onRemove = onRemoveTask
                        )
                    }
                }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(onClick = {
                        onAddTask(newTaskTitle)
                        newTaskTitle = ""
                        showDialog = false
                    }) {
                        Text("Add")
                    }
                },
                dismissButton = {
                    OutlinedButton(onClick = {
                        showDialog = false
                    }) {
                        Text("Cancel")
                    }
                },
                title = { Text("Add New Task") },
                text = {
                    OutlinedTextField(
                        value = newTaskTitle,
                        onValueChange = { newTaskTitle = it },
                        label = { Text("Task Title") },
                        singleLine = true
                    )
                }
            )
        }
    }
}

