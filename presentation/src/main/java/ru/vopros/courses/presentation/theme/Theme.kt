package ru.vopros.courses.presentation.theme

import androidx.activity.ComponentActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat

private val DarkColorScheme = darkColorScheme(
    background = Dark,
    onBackground = White,
    primary = Green,
    onPrimary = White,
)

@Composable
fun CoursesTheme(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val view = LocalView.current

    SideEffect {
        val window = (context as? ComponentActivity)?.window ?: return@SideEffect
        val controller = WindowInsetsControllerCompat(window, view)
        controller.isAppearanceLightStatusBars = false
    }
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}