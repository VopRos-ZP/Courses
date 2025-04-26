package ru.vopros.courses.presentation.screens.courses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _search = MutableStateFlow("")
    val search = _search.asStateFlow()

    private val _courses = MutableStateFlow<List<Course>>(emptyList())
    val courses = _courses.asStateFlow()

    private val _isSorted = MutableStateFlow(false)
    val isSorted = _isSorted.asStateFlow()

    fun fetchCourseList() {
        viewModelScope.launch {
            _courses.value = getCourseListUseCase()
        }
    }

    fun initFavoriteCourseList() {
        viewModelScope.launch {
            courses.collect {
                it.filter { c -> c.hasLike }.forEach { c ->
                    if (!containsCourseInFavoritesUseCase(c))
                        insertFavoriteCourseUseCase(c)
                }
            }
        }
    }

    fun onFilterByDateClick() {
        _courses.value = when (isSorted.value) {
            true -> courses.value.sortedBy { it.id }
            else -> courses.value.sortedByDescending { it.publishDate }
        }
        _isSorted.value = !isSorted.value
    }

    fun onFavoriteCourseClick(course: Course) {
        viewModelScope.launch {
            when (containsCourseInFavoritesUseCase(course)) {
                true -> deleteFavoriteCourseUseCase(course)
                else -> insertFavoriteCourseUseCase(course.copy(hasLike = true))
            }
        }
    }

}