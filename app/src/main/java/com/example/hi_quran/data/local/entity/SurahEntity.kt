package com.example.hi_quran.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hi_quran.domain.model.Surah

@Entity(tableName = "surah")
data class SurahEntity(
    @PrimaryKey val number: Int,
    val name: String,
    val englishName: String,
    val englishNameTranslation: String,
    val numberOfAyahs: Int,
    val revelationType: String
)

fun SurahEntity.toDomain() = Surah(
    number = number,
    name = name,
    englishName = englishName,
    englishNameTranslation = englishNameTranslation,
    numberOfAyahs = numberOfAyahs,
    revelationType = revelationType
)

fun Surah.toEntity() = SurahEntity(
    number = number,
    name = name,
    englishName = englishName,
    englishNameTranslation = englishNameTranslation,
    numberOfAyahs = numberOfAyahs,
    revelationType = revelationType
)
