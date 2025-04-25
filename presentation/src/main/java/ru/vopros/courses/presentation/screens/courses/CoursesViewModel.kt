package ru.vopros.courses.presentation.screens.courses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.vopros.courses.domain.model.Course
import ru.vopros.courses.domain.usecase.GetCourseListUseCase

class CoursesViewModel(
    private val getCourseListUseCase: GetCourseListUseCase
) : ViewModel() {

    private val _search = MutableStateFlow("")
    val search = _search.asStateFlow()

    private val _courses = MutableStateFlow<List<Course>>(emptyList())
    val courses = _courses.asStateFlow()

    fun fetchCourseList() {
        viewModelScope.launch {
            _courses.value = getCourseListUseCase()
        }
    }

}