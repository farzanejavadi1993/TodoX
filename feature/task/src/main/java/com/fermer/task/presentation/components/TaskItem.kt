package com.fermer.task.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fermer.common.util.toFormattedDate
import com.fermer.model.Priority
import com.fermer.model.TaskModel
//
//@Composable
//fun TaskItem(
//    task: TaskModel,
//    onDelete: (String) -> Unit,
//    onToggleCheck: (TaskModel) -> Unit
//) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 4.dp)
//    ) {
//
//
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 8.dp)
//        ) {
//            Checkbox(
//                checked = task.isDone,
//                onCheckedChange = {
//                    onToggleCheck(task.copy(isDone = it))
//                }
//            )
//            Text(
//                text = task.title,
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(start = 8.dp)
//            )
//            IconButton(onClick = { onDelete(task.id) }) {
//                Icon(Icons.Default.Delete, contentDescription = "Delete")
//            }
//        }
//    }
//
//}
@Composable
fun TaskItem(
    task: TaskModel,
    onCheckedChange: (Boolean) -> Unit,
    onRemove: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Checkbox(
            checked = task.isDone,
            onCheckedChange = onCheckedChange
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = task.title)
            Text(text = task.dueDate!!.toFormattedDate()) // مثلا "18 Sep"
            Text(
                text = task.priority.name,
                color = when (task.priority) {
                    Priority.HIGH -> Color.Red
                    Priority.MEDIUM -> Color.Yellow
                    Priority.LOW -> Color.Green
                }
            )
        }
        IconButton(onClick = onRemove) {
            Icon(Icons.Default.Delete, contentDescription = "Remove Task")
        }
    }
}