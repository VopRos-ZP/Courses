package ru.vopros.courses.domain.usecase

import ru.vopros.courses.domain.model.Course
import ru.vopros.courses.domain.repository.FavoritesRepository

class DeleteFavoriteCourseUseCase(
    private val favoritesRepository: FavoritesRepository
) {

    suspend operator fun invoke(course: Course) {
        favoritesRepository.delete(course)
    }

}