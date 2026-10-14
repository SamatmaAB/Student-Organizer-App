package com.example.studentorganizer.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object Assignments : Screen("assignments")
    object Profile : Screen("profile")
    object Settings : Screen("settings")
    object AddAssignment : Screen("add_assignment")
}

enum class Priority { Low, Medium, High }

data class Assignment(
    val id: String = java.util.UUID.randomUUID().toString(),
    val title: String,
    val course: String,
    val dueDate: String,
    val priority: Priority = Priority.Medium,
    val description: String = "",
    val reminder: Boolean = false,
    val isCompleted: Boolean = false
)

data class Course(val id: String, val name: String, val code: String, val instructor: String, val progress: Float)

class AppState {
    val assignments = mutableStateListOf(
        Assignment(title = "Math Homework", course = "Math 101", dueDate = "2026-10-20", priority = Priority.Medium, description = "Complete chapter 5 exercises", reminder = true),
        Assignment(title = "History Essay", course = "History 101", dueDate = "2026-10-22", priority = Priority.High, description = "Write a 1000-word essay"),
        Assignment(title = "Physics Lab Report", course = "Physics 101", dueDate = "2026-10-18", priority = Priority.Low, description = "Submit lab report", reminder = true),
        Assignment(title = "English Reading", course = "English 101", dueDate = "2026-10-25", priority = Priority.Low, description = "Read chapters 1-3"),
        Assignment(title = "Math Quiz Review", course = "Math 101", dueDate = "2026-10-27", priority = Priority.Medium, description = "Review logarithms and functions"),
        Assignment(title = "History Reading Notes", course = "History 101", dueDate = "2026-10-29", priority = Priority.Low, description = "Prepare notes for the next seminar"),
        Assignment(title = "Physics Problem Set", course = "Physics 101", dueDate = "2026-10-30", priority = Priority.High, description = "Complete the forces worksheet")
    )
    val courses = listOf(
        Course("c1", "Math 101", "MATH-101", "Prof. Smith", .8f),
        Course("c2", "History 101", "HIST-101", "Prof. Lee", .5f),
        Course("c3", "Physics 101", "PHYS-101", "Prof. Kim", .9f),
        Course("c4", "English 101", "ENG-101", "Prof. Brown", .7f)
    )
    var darkTheme by mutableStateOf(false)
    var defaultStartScreen by mutableStateOf<Screen>(Screen.Dashboard)
    var notificationsEnabled by mutableStateOf(false)

    fun toggleAssignment(assignment: Assignment) {
        val index = assignments.indexOfFirst { it.id == assignment.id }
        if (index >= 0) assignments[index] = assignment.copy(isCompleted = !assignment.isCompleted)
    }
}
