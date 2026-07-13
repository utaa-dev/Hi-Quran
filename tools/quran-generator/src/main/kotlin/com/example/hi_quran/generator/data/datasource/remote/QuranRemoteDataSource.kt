package com.example.hi_quran.generator.data.datasource.remote

import com.example.hi_quran.generator.domain.model.Ayah
import com.example.hi_quran.generator.domain.model.Surah

interface QuranRemoteDataSource {
    suspend fun fetchSurahs(): List<Surah>
    suspend fun fetchAyahs(surahNumber: Int): List<Ayah>
}
