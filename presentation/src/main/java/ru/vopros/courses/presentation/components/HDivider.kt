package ru.vopros.courses.presentation.components

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import ru.vopros.courses.presentation.theme.Stroke

@Composable
fun HDivider() {
    HorizontalDivider(
        thickness = 1.dp,
        color = Stroke
    )
}