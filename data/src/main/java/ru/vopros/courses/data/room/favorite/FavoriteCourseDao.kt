package ru.vopros.courses.data.room.favorite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCourseDao {

    @Query("SELECT * FROM favorites")
    fun listenAll(): Flow<List<FavoriteCourseEntity>>

    @Query("SELECT EXISTS(SELECT * FROM favorites WHERE id = :id)")
    suspend fun contains(id: Int): Boolean

    @Insert
    suspend fun insert(entity: FavoriteCourseEntity)

    @Query("DELETE FROM favorites WHERE id = :id")
    suspend fun delete(id: Int)

}