package com.example.hi_quran.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hi_quran.domain.model.Surah

@Entity(tableName = "surah")
data class SurahEntity(
    @PrimaryKey 
    @ColumnInfo(name = "number")
    val number: Int,
    
    @ColumnInfo(name = "name")
    val name: String,
    
    @ColumnInfo(name = "english_name")
    val englishName: String,
    
    @ColumnInfo(name = "english_name_translation")
    val englishNameTranslation: String,
    
    @ColumnInfo(name = "number_of_ayahs")
    val numberOfAyahs: Int,
    
    @ColumnInfo(name = "revelation_type")
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
