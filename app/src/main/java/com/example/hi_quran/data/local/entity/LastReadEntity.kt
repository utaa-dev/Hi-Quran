package com.example.hi_quran.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "last_read")
data class LastReadEntity(
    @PrimaryKey val id: Int = 1, // Always 1 to keep only one last read record
    val surahNumber: Int,
    val ayahNumber: Int,
    val surahName: String,
    val timestamp: Long = System.currentTimeMillis()
)
