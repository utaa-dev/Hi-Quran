package com.example.hi_quran.domain.model

data class Bookmark(
    val id: Int = 0,
    val surahNumber: Int,
    val ayahNumber: Int,
    val surahName: String,
    val createdAt: Long
)
