package ru.vopros.courses.presentation.screens.courses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.vopros.courses.domain.model.Course
import ru.vopros.courses.domain.usecase.ContainsCourseInFavoritesUseCase
import ru.vopros.courses.domain.usecase.DeleteFavoriteCourseUseCase
import ru.vopros.courses.domain.usecase.GetCourseListUseCase
import ru.vopros.courses.domain.usecase.InsertFavoriteCourseUseCase

class CoursesViewModel(
    private val getCourseListUseCase: GetCourseListUseCase,
    private val containsCourseInFavoritesUseCase: ContainsCourseInFavoritesUseCase,
    private val insertFavoriteCourseUseCase: InsertFavoriteCourseUseCase,
    private val deleteFavoriteCourseUseCase: DeleteFavoriteCourseUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(CoursesViewState())
    val state = _state.asStateFlow()

    init {
        fetchCourseList()
    }

    private fun fetchCourseList() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val courses = getCourseListUseCase()
                _state.update { it.copy(courses = courses, isLoading = false) }
                initFavoriteCourseList(courses)
            } catch (_: Exception) {
                // show error
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    private suspend fun initFavoriteCourseList(courses: List<Course>) {
        courses.filter { !it.hasLike }.forEach { course ->
            if (containsCourseInFavoritesUseCase(course)) {
                deleteFavoriteCourseUseCase(course)
            }
        }
        courses.filter { it.hasLike }.forEach { course ->
            if (!containsCourseInFavoritesUseCase(course)) {
                insertFavoriteCourseUseCase(course)
            }
        }
    }

    fun onFilterByDateClick() {
        val currentState = _state.value
        val sortedCourses = when (currentState.isSorted) {
            true -> currentState.courses.sortedBy { it.id }
            else -> currentState.courses.sortedByDescending { it.publishDate }
        }
        _state.update {
            it.copy(
                courses = sortedCourses,
                isSorted = !currentState.isSorted
            )
        }
    }

    fun onFavoriteCourseClick(course: Course) {
        viewModelScope.launch {
            val isFavorite = containsCourseInFavoritesUseCase(course)
            when (isFavorite) {
                true -> deleteFavoriteCourseUseCase(course)
                else -> insertFavoriteCourseUseCase(course.copy(hasLike = true))
            }
            updateCourseLikeStatus(course, !isFavorite)
        }
    }

    private fun updateCourseLikeStatus(course: Course, hasLike: Boolean) {
        _state.update {
            it.copy(
                courses = it.courses.map { c ->
                    if (c.id == course.id) c.copy(hasLike = hasLike) else c
                }
            )
        }
    }

}