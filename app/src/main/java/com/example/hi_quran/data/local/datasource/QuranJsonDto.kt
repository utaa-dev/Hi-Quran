package com.example.hi_quran.data.local.datasource

import kotlinx.serialization.Serializable

@Serializable
data class QuranResponseDto(
    val surahs: List<SurahDto>
)

@Serializable
data class SurahDto(
    val number: Int,
    val name: String,
    val englishName: String,
    val englishNameTranslation: String,
    val revelationType: String,
    val ayahs: List<AyahDto>
)

@Serializable
data class AyahDto(
    val number: Int,
    val text: String,
    val transliteration: String = "",
    val translation: String,
    val juz: Int,
    val page: Int
)

@Serializable
data class JuzResponseDto(
    val juzList: List<JuzDto>
)

@Serializable
data class JuzDto(
    val number: Int,
    val name: String,
    val startSurahNumber: Int,
    val startAyahNumber: Int
)

@Serializable
data class DoaResponseDto(
    val doas: List<DoaDto>
)

@Serializable
data class DoaDto(
    val id: Int,
    val title: String,
    val arabic: String,
    val translation: String,
    val source: String
)
