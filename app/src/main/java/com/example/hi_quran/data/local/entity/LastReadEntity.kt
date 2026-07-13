package com.example.hi_quran.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "last_read")
data class LastReadEntity(
    @PrimaryKey val id: Int = 1, // Always 1 to keep only one last read record
    @ColumnInfo(name = "surah_number") val surahNumber: Int,
    @ColumnInfo(name = "ayah_number") val ayahNumber: Int,
    @ColumnInfo(name = "surah_name") val surahName: String,
    val timestamp: Long = System.currentTimeMillis()
)
