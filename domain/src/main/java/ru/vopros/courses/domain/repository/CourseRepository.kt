package ru.vopros.courses.domain.repository

import ru.vopros.courses.domain.model.Course

interface CourseRepository {
    suspend fun fetchCourses(): List<Course>
}