package ru.vopros.courses.presentation.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.vopros.courses.domain.model.Course
import ru.vopros.courses.domain.usecase.DeleteFavoriteCourseUseCase
import ru.vopros.courses.domain.usecase.GetFavoriteCourseListUseCase

class FavoritesViewModel(
    private val getFavoriteCourseListUseCase: GetFavoriteCourseListUseCase,
    private val deleteFavoriteCourseUseCase: DeleteFavoriteCourseUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(FavoritesViewState())
    val state = _state.asStateFlow()

    init {
        fetchFavoriteCourses()
    }

    private fun fetchFavoriteCourses() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                getFavoriteCourseListUseCase().collect { courses ->
                    _state.update { it.copy(courses = courses, isLoading = false) }
                }
            } catch (_: Exception) {
                // show error
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    fun onRemoveFavoriteCourseClick(course: Course) {
        viewModelScope.launch {
            deleteFavoriteCourseUseCase(course)
        }
    }

}