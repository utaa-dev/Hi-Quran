package com.example.hi_quran.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hi_quran.domain.model.Ayah

@Entity(tableName = "ayah")
data class AyahEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val number: Int,
    val text: String,
    val transliteration: String = "",
    val translation: String,
    val surahNumber: Int,
    val juz: Int,
    val manzil: Int,
    val page: Int,
    val ruku: Int,
    val hizbQuarter: Int,
    val sajda: Boolean
)

fun AyahEntity.toDomain() = Ayah(
    number = number,
    text = text,
    transliteration = transliteration,
    translation = translation,
    surahNumber = surahNumber,
    juz = juz,
    manzil = manzil,
    page = page,
    ruku = ruku,
    hizbQuarter = hizbQuarter,
    sajda = sajda
)

fun Ayah.toEntity() = AyahEntity(
    number = number,
    text = text,
    transliteration = transliteration,
    translation = translation,
    surahNumber = surahNumber,
    juz = juz,
    manzil = manzil,
    page = page,
    ruku = ruku,
    hizbQuarter = hizbQuarter,
    sajda = sajda
)
