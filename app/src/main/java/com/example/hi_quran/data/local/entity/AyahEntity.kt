package com.example.hi_quran.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.example.hi_quran.domain.model.Ayah

@Entity(
    tableName = "ayah",
    primaryKeys = ["surah_number", "number"],
    foreignKeys = [
        ForeignKey(
            entity = SurahEntity::class,
            parentColumns = ["number"],
            childColumns = ["surah_number"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["surah_number"], name = "idx_ayah_surah_number")
    ]
)
data class AyahEntity(
    @ColumnInfo(name = "surah_number") val surahNumber: Int,
    val number: Int,
    @ColumnInfo(name = "text_arabic") val text: String,
    @ColumnInfo(name = "text_arabic_plain") val textPlain: String,
    @ColumnInfo(name = "text_latin") val transliteration: String = "",
    @ColumnInfo(name = "text_indonesia") val translation: String,
    @ColumnInfo(defaultValue = "0") val juz: Int,
    @ColumnInfo(defaultValue = "0") val manzil: Int,
    @ColumnInfo(defaultValue = "0") val page: Int,
    @ColumnInfo(defaultValue = "0") val ruku: Int,
    @ColumnInfo(name = "hizb_quarter", defaultValue = "0") val hizbQuarter: Int,
    @ColumnInfo(defaultValue = "0") val sajda: Boolean
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
    surahNumber = surahNumber,
    number = number,
    text = text,
    textPlain = "", // This might need to be passed if available
    transliteration = transliteration,
    translation = translation,
    juz = juz,
    manzil = manzil,
    page = page,
    ruku = ruku,
    hizbQuarter = hizbQuarter,
    sajda = sajda
)
