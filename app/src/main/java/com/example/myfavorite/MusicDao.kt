package com.example.myfavorite

// MusicDao.kt
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MusicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(music: Music)

    @Delete
    suspend fun delete(music: Music)

    @Query("SELECT * FROM music_table WHERE isFavorite = 1")
    suspend fun getAllFavorites(): List<Music>
}