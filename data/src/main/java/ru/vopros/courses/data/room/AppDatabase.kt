package ru.vopros.courses.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.vopros.courses.data.room.favorite.FavoriteCourseDao
import ru.vopros.courses.data.room.favorite.FavoriteCourseEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [
        FavoriteCourseEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract val favoriteCourseDao: FavoriteCourseDao

    companion object {
        const val NAME = "app_database"
    }
}