package com.example.hi_quran.domain.model

data class LastRead(
    val surahNumber: Int,
    val ayahNumber: Int,
    val surahName: String,
    val timestamp: Long
)
