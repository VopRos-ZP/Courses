package ru.vopros.courses.presentation.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.vopros.courses.domain.model.Course
import ru.vopros.courses.domain.usecase.DeleteFavoriteCourseUseCase
import ru.vopros.courses.domain.usecase.GetFavoriteCourseListUseCase

class FavoritesViewModel(
    private val getFavoriteCourseListUseCase: GetFavoriteCourseListUseCase,
    private val deleteFavoriteCourseUseCase: DeleteFavoriteCourseUseCase,
) : ViewModel() {

    private val _courses = MutableStateFlow<List<Course>>(emptyList())
    val courses = _courses.asStateFlow()

    fun fetchFavoriteCourses() {
        viewModelScope.launch {
            getFavoriteCourseListUseCase().collect {
                _courses.emit(it)
            }
        }
    }

    fun onRemoveFavoriteCourseClick(course: Course) {
        viewModelScope.launch {
            deleteFavoriteCourseUseCase(course)
        }
    }

}