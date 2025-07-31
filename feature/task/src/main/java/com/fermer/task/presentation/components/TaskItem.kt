package com.fermer.task.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fermer.model.TaskModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(
    task: TaskModel,
    onRemove: (String) -> Unit
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            if (value == SwipeToDismissBoxValue.EndToStart) {
                onRemove(task.id)
                true
            } else false
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete task",
                    tint = Color.White
                )
            }
        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                if (task.isDone) {
                    Text(text = "✅")
                }
            }
        }
    }
}
