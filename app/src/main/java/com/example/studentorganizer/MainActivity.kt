package com.example.studentorganizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.studentorganizer.data.AppState
import com.example.studentorganizer.data.Screen
import com.example.studentorganizer.ui.components.BottomBar
import com.example.studentorganizer.ui.screens.AddAssignmentScreen
import com.example.studentorganizer.ui.screens.AssignmentsScreen
import com.example.studentorganizer.ui.screens.DashboardScreen
import com.example.studentorganizer.ui.screens.ProfileScreen
import com.example.studentorganizer.ui.screens.SettingsScreen
import com.example.studentorganizer.ui.theme.StudentOrganizerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val appState = remember { AppState() }
            StudentOrganizerTheme(darkTheme = appState.darkTheme) {
                Surface(Modifier.fillMaxSize()) { StudentOrganizerApp(appState) }
            }
        }
    }
}

@androidx.compose.runtime.Composable
private fun StudentOrganizerApp(appState: AppState) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.AddAssignment.route) {
                BottomBar(navController, currentRoute)
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = appState.defaultStartScreen.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Dashboard.route) { DashboardScreen(navController, appState) }
            composable(Screen.Assignments.route) { AssignmentsScreen(navController, appState) }
            composable(Screen.Profile.route) { ProfileScreen(navController, appState) }
            composable(Screen.Settings.route) { SettingsScreen(navController, appState) }
            composable(Screen.AddAssignment.route) { AddAssignmentScreen(navController, appState) }
        }
    }
}
