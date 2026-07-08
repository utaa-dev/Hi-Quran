package com.example.hi_quran.domain.model

data class Ayah(
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
