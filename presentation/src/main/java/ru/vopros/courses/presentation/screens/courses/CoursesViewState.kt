package ru.vopros.courses.presentation.screens.courses

import ru.vopros.courses.domain.model.Course

data class CoursesViewState(
    val search: String = "",
    val courses: List<Course> = emptyList(),
    val isLoading: Boolean = false,
    val isSorted: Boolean = false,
)
