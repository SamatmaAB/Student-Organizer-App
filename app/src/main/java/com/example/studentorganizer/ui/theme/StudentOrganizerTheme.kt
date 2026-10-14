package com.example.studentorganizer.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Composable
fun StudentOrganizerTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) {
            darkColorScheme(
                primary = Color(0xFF6200EE),
                onPrimary = Color(0xFFFFFFFF),
                primaryContainer = Color(0xFFBB86FC),
                onPrimaryContainer = Color(0xFF220055),
                secondary = Color(0xFF03DAC6),
                onSecondary = Color(0xFF000000),
                secondaryContainer = Color(0xFF03DAC6),
                onSecondaryContainer = Color(0xFF00221A),
                tertiary = Color(0xFFCFD8DC),
                onTertiary = Color(0xFF000000),
                background = Color(0xFF121212),
                onBackground = Color(0xFFDDDDDD),
                surface = Color(0xFF121212),
                onSurface = Color(0xFFDDDDDD),
                surfaceVariant = Color(0xFF1E1E1E),
                onSurfaceVariant = Color(0xFFB0B0B0),
                outline = Color(0xFF555555)
            )
        } else {
            lightColorScheme(
                primary = Color(0xFF6200EE),
                onPrimary = Color(0xFFFFFFFF),
                primaryContainer = Color(0xFFBB86FC),
                onPrimaryContainer = Color(0xFF220055),
                secondary = Color(0xFF03DAC6),
                onSecondary = Color(0xFF000000),
                secondaryContainer = Color(0xFF03DAC6),
                onSecondaryContainer = Color(0xFF00221A),
                tertiary = Color(0xFFCFD8DC),
                onTertiary = Color(0xFF000000),
                background = Color(0xFFFFFFFF),
                onBackground = Color(0xFF000000),
                surface = Color(0xFFFFFFFF),
                onSurface = Color(0xFF000000),
                surfaceVariant = Color(0xFFF5F5F5),
                onSurfaceVariant = Color(0xFF757575),
                outline = Color(0xFFBDBDBD)
            )
        },
        typography = Typography(),
        content = content
    )
}
