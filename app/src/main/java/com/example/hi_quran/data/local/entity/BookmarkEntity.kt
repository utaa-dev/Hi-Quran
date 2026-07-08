package com.example.hi_quran.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val surahNumber: Int,
    val ayahNumber: Int,
    val surahName: String,
    val createdAt: Long = System.currentTimeMillis()
)
