package com.example.studentorganizer.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.studentorganizer.R
import com.example.studentorganizer.data.AppState
import com.example.studentorganizer.data.Screen
import com.example.studentorganizer.ui.components.AppBar
import com.example.studentorganizer.ui.components.AssignmentCard
import com.example.studentorganizer.ui.components.CourseInfo

@Composable
fun DashboardScreen(navController: NavHostController, appState: AppState) {
    Scaffold(
        topBar = { AppBar(stringResource(R.string.dashboard_title)) },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = { navController.navigate(Screen.AddAssignment.route) }, icon = { Icon(Icons.Default.Add, null) }, text = { Text(stringResource(R.string.add_assignment)) })
        }
    ) { padding ->
        LazyColumn(Modifier.fillMaxSize().padding(padding).padding(horizontal = 16.dp)) {
            item {
                Text(stringResource(R.string.welcome_back, "Student"), style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(vertical = 16.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    StatCard(stringResource(R.string.active_courses), appState.courses.size.toString(), Modifier.weight(1f))
                    StatCard(stringResource(R.string.pending_tasks), appState.assignments.count { !it.isCompleted }.toString(), Modifier.weight(1f))
                }
                Text(stringResource(R.string.active_courses), style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(top = 20.dp, bottom = 8.dp))
            }
            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(appState.courses) { course ->
                        CourseInfo(course.name, course.code, course.instructor, course.progress)
                    }
                }
                Text(stringResource(R.string.urgent_deadlines), style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(top = 20.dp, bottom = 8.dp))
            }
            val urgent = appState.assignments.filter { !it.isCompleted }.sortedBy { it.dueDate }.take(2)
            if (urgent.isEmpty()) item { Text(stringResource(R.string.no_upcoming), modifier = Modifier.padding(vertical = 16.dp)) }
            items(urgent, key = { it.id }) { assignment ->
                AssignmentCard(assignment.title, assignment.course, assignment.dueDate, assignment.priority, assignment.isCompleted) { appState.toggleAssignment(assignment) }
            }
            item { Column(Modifier.padding(bottom = 88.dp)) {} }
        }
    }
}

@Composable
private fun StatCard(label: String, value: String, modifier: Modifier = Modifier) {
    Card(modifier, colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
        Column(Modifier.padding(16.dp)) {
            Text(label, style = MaterialTheme.typography.titleSmall)
            Text(value, style = MaterialTheme.typography.headlineMedium)
        }
    }
}
