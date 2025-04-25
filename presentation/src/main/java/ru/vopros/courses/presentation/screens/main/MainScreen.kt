package ru.vopros.courses.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import ru.vopros.courses.presentation.theme.Dark

@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel()
) {
    val courses by viewModel.courses.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark)
    )
    LaunchedEffect(Unit) {
        viewModel.fetchCourseList()
    }
}