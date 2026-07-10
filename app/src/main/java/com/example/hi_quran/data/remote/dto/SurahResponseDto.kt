package com.example.hi_quran.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class SurahResponseDto(
    val code: Int,
    val message: String,
    val data: List<RemoteSurahDto>
)

@Serializable
data class RemoteSurahDto(
    val nomor: Int,
    val nama: String,
    val namaLatin: String,
    val jumlahAyat: Int,
    val tempatTurun: String,
    val arti: String,
    val deskripsi: String
)
