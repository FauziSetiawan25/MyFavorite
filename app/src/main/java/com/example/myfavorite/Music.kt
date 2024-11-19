package com.example.myfavorite

// Music.kt
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "music_table")
data class Music(
    @PrimaryKey val id: Int,
    val title: String,
    var isFavorite: Boolean = false
)
