package com.example.hi_quran.generator.domain.model

data class Surah(
    val number: Int,
    val name: String,
    val englishName: String,
    val translation: String,
    val numberOfAyahs: Int,
    val revelationType: String
)
