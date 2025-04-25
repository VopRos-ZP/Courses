package ru.vopros.courses.data.model

import java.util.Date

data class CourseDto(
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: Date,
    val hasLike: Boolean,
    val publishDate: Date
)
