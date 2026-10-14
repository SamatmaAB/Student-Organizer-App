package com.example.studentorganizer.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.studentorganizer.R
import com.example.studentorganizer.data.AppState
import com.example.studentorganizer.data.Screen
import com.example.studentorganizer.ui.components.AppBar

@Composable
fun SettingsScreen(navController: NavHostController, appState: AppState) {
    Scaffold(topBar = { AppBar(stringResource(R.string.settings_title)) }) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(stringResource(R.string.dark_mode))
                Switch(checked = appState.darkTheme, onCheckedChange = { appState.darkTheme = it })
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(stringResource(R.string.notifications))
                Checkbox(checked = appState.notificationsEnabled, onCheckedChange = { appState.notificationsEnabled = it })
            }
            Text(stringResource(R.string.default_start), modifier = Modifier.padding(top = 16.dp))
            listOf(Screen.Dashboard, Screen.Assignments).forEach { screen ->
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = appState.defaultStartScreen == screen, onClick = { appState.defaultStartScreen = screen })
                    Text(if (screen == Screen.Dashboard) stringResource(R.string.dashboard_start) else stringResource(R.string.assignments_start))
                }
            }
        }
    }
}
