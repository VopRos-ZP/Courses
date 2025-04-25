package ru.vopros.courses.domain.usecase

import ru.vopros.courses.domain.model.Course
import ru.vopros.courses.domain.repository.CourseRepository

class GetCourseListUseCase(
    private val courseRepository: CourseRepository
) {

    suspend operator fun invoke(): List<Course> {
        return courseRepository.fetchCourses()
    }

}