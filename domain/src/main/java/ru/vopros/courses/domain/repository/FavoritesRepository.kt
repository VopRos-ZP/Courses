package ru.vopros.courses.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.vopros.courses.domain.model.Course

interface FavoritesRepository {
    val favoriteCourses: Flow<List<Course>>
    suspend fun contains(course: Course): Boolean
    suspend fun insert(course: Course)
    suspend fun delete(course: Course)
}