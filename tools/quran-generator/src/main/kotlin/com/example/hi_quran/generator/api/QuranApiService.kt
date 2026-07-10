package com.example.hi_quran.generator.api

import com.example.hi_quran.generator.constant.AppConstants
import com.example.hi_quran.generator.model.SurahDetailResponse
import com.example.hi_quran.generator.model.SurahListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface QuranApiService {
    @GET(AppConstants.ENDPOINT_SURAH_LIST)
    suspend fun getSurahList(): SurahListResponse

    @GET(AppConstants.ENDPOINT_SURAH_DETAIL)
    suspend fun getSurahDetail(
        @Path("nomor") nomor: Int
    ): SurahDetailResponse
}
