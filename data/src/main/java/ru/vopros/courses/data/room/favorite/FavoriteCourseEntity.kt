package ru.vopros.courses.data.room.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.vopros.courses.data.room.DateConverter
import java.util.Date

@Entity(tableName = "favorites")
@TypeConverters(DateConverter::class)
data class FavoriteCourseEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: Date,
    val hasLike: Boolean,
    val publishDate: Date
)