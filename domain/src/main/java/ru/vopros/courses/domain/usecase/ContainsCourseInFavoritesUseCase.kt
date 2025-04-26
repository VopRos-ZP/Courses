package ru.vopros.courses.domain.usecase

import ru.vopros.courses.domain.model.Course
import ru.vopros.courses.domain.repository.FavoritesRepository

class ContainsCourseInFavoritesUseCase(
    private val favoritesRepository: FavoritesRepository
) {

    suspend operator fun invoke(course: Course) =
        favoritesRepository.contains(course)

}