package ru.vopros.courses.presentation.screens.favorites

import ru.vopros.courses.domain.model.Course

data class FavoritesViewState(
    val courses: List<Course> = emptyList(),
    val isLoading: Boolean = false,
)
