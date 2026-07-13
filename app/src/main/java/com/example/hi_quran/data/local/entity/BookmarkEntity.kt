package com.example.hi_quran.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "surah_number") val surahNumber: Int,
    @ColumnInfo(name = "ayah_number") val ayahNumber: Int,
    @ColumnInfo(name = "surah_name") val surahName: String,
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis()
)
