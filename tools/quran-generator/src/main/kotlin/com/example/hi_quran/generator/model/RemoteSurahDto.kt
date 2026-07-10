package com.example.hi_quran.generator.model

import kotlinx.serialization.Serializable

@Serializable
data class SurahListResponse(
    val code: Int,
    val message: String,
    val data: List<SurahItemDto>
)

@Serializable
data class SurahDetailResponse(
    val code: Int,
    val message: String,
    val data: SurahDetailDto
)

@Serializable
data class SurahItemDto(
    val nomor: Int,
    val nama: String,
    val namaLatin: String,
    val jumlahAyat: Int,
    val tempatTurun: String,
    val arti: String,
    val deskripsi: String
)

@Serializable
data class SurahDetailDto(
    val nomor: Int,
    val nama: String,
    val namaLatin: String,
    val jumlahAyat: Int,
    val tempatTurun: String,
    val arti: String,
    val deskripsi: String,
    val ayat: List<AyahDto>
)

@Serializable
data class AyahDto(
    val nomorAyat: Int,
    val teksArab: String,
    val teksLatin: String,
    val teksIndonesia: String
)
