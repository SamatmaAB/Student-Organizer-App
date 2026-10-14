package com.example.studentorganizer.ui.screens

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.studentorganizer.R
import com.example.studentorganizer.data.AppState
import com.example.studentorganizer.ui.components.AppBar
import com.example.studentorganizer.ui.components.StudentProfile

@Composable
fun ProfileScreen(navController: NavHostController, appState: AppState) {
    var name by remember { mutableStateOf("Student Name") }
    var major by remember { mutableStateOf("Computer Science") }
    var editing by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Scaffold(topBar = { AppBar(stringResource(R.string.profile_title)) }) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            StudentProfile(name, "123456789", major, 0)
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(stringResource(R.string.academic_stats), style = MaterialTheme.typography.titleLarge)
                    Text(stringResource(R.string.gpa))
                    Text(stringResource(R.string.credits))
                    Text(stringResource(R.string.semester))
                }
            }
            Button(onClick = { editing = true }, modifier = Modifier.fillMaxWidth()) { Text(stringResource(R.string.edit_profile)) }
            Button(onClick = {
                val summary = "Academic Summary\n$name\nMajor: $major\nGPA: 3.82\nCompleted Credits: 84\nCurrent Semester: Fall 2026"
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_SUBJECT, "Academic Summary")
                    putExtra(Intent.EXTRA_TEXT, summary)
                }
                context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.export_summary)))
            }, modifier = Modifier.fillMaxWidth()) { Text(stringResource(R.string.export_summary)) }
        }
    }
    if (editing) {
        AlertDialog(
            onDismissRequest = { editing = false },
            title = { Text(stringResource(R.string.edit_profile)) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(name, { name = it }, label = { Text(stringResource(R.string.student_name_default)) }, singleLine = true)
                    OutlinedTextField(major, { major = it }, label = { Text(stringResource(R.string.major_default)) }, singleLine = true)
                }
            },
            confirmButton = { TextButton(onClick = { editing = false }) { Text(stringResource(R.string.save)) } },
            dismissButton = { TextButton(onClick = { editing = false }) { Text(stringResource(R.string.cancel)) } }
        )
    }
}
