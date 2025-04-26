package ru.vopros.courses.data.repository

import com.google.gson.Gson
import ru.vopros.courses.data.mapper.fromDto
import ru.vopros.courses.data.model.CourseDto
import ru.vopros.courses.data.model.CourseListDto
import ru.vopros.courses.data.retrofit.CourseApi
import ru.vopros.courses.domain.model.Course
import ru.vopros.courses.domain.repository.CourseRepository

class CourseRepositoryImpl(
    private val gson: Gson,
    private val courseApi: CourseApi
) : CourseRepository {

    override suspend fun fetchCourses(): List<Course> {
        val body = courseApi.getCourses().body() ?: return emptyList()
        return gson
            .fromJson(body.string(), CourseListDto::class.java)
            .courses
            .map(CourseDto::fromDto)
    }

}