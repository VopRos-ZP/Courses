package ru.vopros.courses.data.repository

import kotlinx.coroutines.flow.map
import ru.vopros.courses.data.mapper.fromEntity
import ru.vopros.courses.data.mapper.toFavoriteEntity
import ru.vopros.courses.data.room.AppDatabase
import ru.vopros.courses.data.room.favorite.FavoriteCourseEntity
import ru.vopros.courses.domain.model.Course
import ru.vopros.courses.domain.repository.FavoritesRepository

class FavoritesRepositoryImpl(
    private val appDatabase: AppDatabase
) : FavoritesRepository {

    override val favoriteCourses = appDatabase.favoriteCourseDao
        .listenAll()
        .map { it.map(FavoriteCourseEntity::fromEntity) }

    override suspend fun contains(course: Course): Boolean {
        return appDatabase.favoriteCourseDao.contains(course.id)
    }

    override suspend fun insert(course: Course) {
        appDatabase.favoriteCourseDao.insert(course.toFavoriteEntity())
    }

    override suspend fun delete(course: Course) {
        appDatabase.favoriteCourseDao.delete(course.id)
    }

}