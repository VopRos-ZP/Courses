package ru.vopros.courses.data.model

import ru.vopros.courses.domain.model.Course

fun CourseDto.fromDto(): Course = Course(
    id = id,
    title = title,
    text = text,
    price = price,
    rate = rate,
    startDate = startDate,
    hasLike = hasLike,
    publishDate = publishDate,
)