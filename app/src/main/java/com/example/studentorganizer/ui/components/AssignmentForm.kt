package com.example.studentorganizer.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import com.example.studentorganizer.R
import com.example.studentorganizer.data.Priority

@Composable
fun AssignmentForm(
    title: String, onTitleChanged: (String) -> Unit,
    course: String, onCourseChanged: (String) -> Unit,
    dueDate: String, onDueDateChanged: (String) -> Unit,
    priority: Priority, onPriorityChanged: (Priority) -> Unit,
    description: String, onDescriptionChanged: (String) -> Unit,
    reminder: Boolean, onReminderChanged: (Boolean) -> Unit,
    onPickDate: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            OutlinedTextField(title, onTitleChanged, label = { Text(stringResource(R.string.title_hint)) }, modifier = Modifier.fillMaxWidth(), singleLine = true, isError = title.isBlank())
        }
        item {
            OutlinedTextField(course, onCourseChanged, label = { Text(stringResource(R.string.course_hint)) }, modifier = Modifier.fillMaxWidth(), singleLine = true, isError = course.isBlank())
        }
        item {
            OutlinedTextField(
                dueDate, onDueDateChanged, label = { Text(stringResource(R.string.due_date_hint)) },
                modifier = Modifier.fillMaxWidth(), singleLine = true, isError = dueDate.isBlank(),
                trailingIcon = { IconButton(onClick = onPickDate) { Icon(Icons.Default.CalendarToday, contentDescription = stringResource(R.string.pick_date)) } }
            )
        }
        item {
            Column {
                Text(stringResource(R.string.priority), style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Priority.values().forEach { option ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(selected = priority == option, onClick = { onPriorityChanged(option) })
                            Text(option.name)
                        }
                    }
                }
            }
        }
        item {
            OutlinedTextField(description, onDescriptionChanged, label = { Text(stringResource(R.string.description_hint)) }, modifier = Modifier.fillMaxWidth(), minLines = 3, maxLines = 5)
        }
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(reminder, onReminderChanged)
                Text(stringResource(R.string.reminder_label), modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}
