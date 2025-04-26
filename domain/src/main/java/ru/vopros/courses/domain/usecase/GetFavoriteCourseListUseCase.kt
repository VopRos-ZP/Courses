package ru.vopros.courses.domain.usecase

import ru.vopros.courses.domain.repository.FavoritesRepository

class GetFavoriteCourseListUseCase(
    private val favoritesRepository: FavoritesRepository
) {

    operator fun invoke() = favoritesRepository.favoriteCourses

}