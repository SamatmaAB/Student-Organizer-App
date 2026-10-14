package com.example.studentorganizer.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.studentorganizer.data.Priority

@Composable
fun AssignmentCard(
    title: String,
    course: String,
    dueDate: String,
    priority: Priority,
    isCompleted: Boolean,
    onToggleComplete: () -> Unit = {}
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = if (isCompleted) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface)
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(title, Modifier.weight(1f), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                Checkbox(checked = isCompleted, onCheckedChange = { onToggleComplete() })
            }
            Text(course, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Icon(Icons.Default.CalendarToday, null, modifier = Modifier.size(16.dp), tint = MaterialTheme.colorScheme.primary)
                Text(dueDate, style = MaterialTheme.typography.bodySmall)
                Icon(
                    when (priority) {
                        Priority.Low -> Icons.Default.CheckCircle
                        Priority.Medium -> Icons.Default.Circle
                        Priority.High -> Icons.Default.Warning
                    }, null, modifier = Modifier.padding(start = 12.dp).size(16.dp),
                    tint = when (priority) {
                        Priority.Low -> MaterialTheme.colorScheme.primary
                        Priority.Medium -> MaterialTheme.colorScheme.tertiary
                        Priority.High -> MaterialTheme.colorScheme.error
                    }
                )
                Text(priority.name, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
