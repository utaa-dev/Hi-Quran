package com.example.hi_quran.data.remote.api

import com.example.hi_quran.data.remote.dto.SurahResponseDto
import retrofit2.http.GET

interface QuranApi {
    @GET("surat")
    suspend fun getSurahs(): SurahResponseDto
}
