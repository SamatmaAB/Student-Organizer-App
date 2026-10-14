package com.example.studentorganizer.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.studentorganizer.R
import com.example.studentorganizer.data.AppState
import com.example.studentorganizer.data.Screen
import com.example.studentorganizer.ui.components.AppBar
import com.example.studentorganizer.ui.components.AssignmentCard

private enum class AssignmentFilter { All, Pending, Completed }

@Composable
fun AssignmentsScreen(navController: NavHostController, appState: AppState) {
    var filter by remember { mutableStateOf(AssignmentFilter.All) }
    val visibleAssignments = appState.assignments.filter {
        when (filter) {
            AssignmentFilter.All -> true
            AssignmentFilter.Pending -> !it.isCompleted
            AssignmentFilter.Completed -> it.isCompleted
        }
    }
    Scaffold(
        topBar = { AppBar(stringResource(R.string.my_assignments)) },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.AddAssignment.route) }) {
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add_assignment))
            }
        }
    ) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(horizontal = 16.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                AssignmentFilter.values().forEach { option ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = filter == option, onClick = { filter = option })
                        Text(when (option) {
                            AssignmentFilter.All -> stringResource(R.string.all)
                            AssignmentFilter.Pending -> stringResource(R.string.pending)
                            AssignmentFilter.Completed -> stringResource(R.string.completed)
                        }, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
            if (visibleAssignments.isEmpty()) {
                Text(stringResource(R.string.no_assignments), modifier = Modifier.padding(top = 24.dp))
            } else {
                LazyColumn(Modifier.fillMaxSize()) {
                    items(visibleAssignments, key = { it.id }) { assignment ->
                        AssignmentCard(assignment.title, assignment.course, assignment.dueDate, assignment.priority, assignment.isCompleted) {
                            appState.toggleAssignment(assignment)
                        }
                    }
                    item { Column(Modifier.padding(bottom = 88.dp)) {} }
                }
            }
        }
    }
}
