package com.example.hi_quran.generator.domain.model

data class Ayah(
    val surahNumber: Int,
    val number: Int,
    val textArabic: String,
    val textArabicPlain: String,
    val textLatin: String,
    val textIndonesia: String,
    val juz: Int = 0,
    val manzil: Int = 0,
    val page: Int = 0,
    val ruku: Int = 0,
    val hizbQuarter: Int = 0,
    val sajda: Boolean = false
)
