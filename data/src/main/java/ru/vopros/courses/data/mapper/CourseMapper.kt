package ru.vopros.courses.data.mapper

import ru.vopros.courses.data.model.CourseDto
import ru.vopros.courses.data.room.favorite.FavoriteCourseEntity
import ru.vopros.courses.domain.Utils.formatDateToRussian
import ru.vopros.courses.domain.Utils.minusRub
import ru.vopros.courses.domain.Utils.parseRussianToDate
import ru.vopros.courses.domain.Utils.plusRub
import ru.vopros.courses.domain.model.Course

fun CourseDto.fromDto(): Course = Course(
    id = id,
    title = title,
    text = text,
    price = price.plusRub(),
    rate = rate,
    startDate = startDate.formatDateToRussian(),
    hasLike = hasLike,
    publishDate = publishDate,
)

fun FavoriteCourseEntity.fromEntity(): Course = Course(
    id = id,
    title = title,
    text = text,
    price = price.plusRub(),
    rate = rate,
    startDate = startDate.formatDateToRussian(),
    hasLike = hasLike,
    publishDate = publishDate,
)

fun Course.toFavoriteEntity(): FavoriteCourseEntity = FavoriteCourseEntity(
    id = id,
    title = title,
    text = text,
    price = price.minusRub(),
    rate = rate,
    startDate = startDate.parseRussianToDate(),
    hasLike = hasLike,
    publishDate = publishDate,
)