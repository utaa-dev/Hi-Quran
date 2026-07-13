package com.example.hi_quran.generator.domain.repository

import com.example.hi_quran.generator.domain.model.Ayah
import com.example.hi_quran.generator.domain.model.GeneratorMetadata
import com.example.hi_quran.generator.domain.model.Surah

interface QuranRepository {
    suspend fun fetchSurahs(): List<Surah>
    suspend fun fetchAyahs(surahNumber: Int): List<Ayah>
    suspend fun saveSurahs(surahs: List<Surah>)
    suspend fun saveAyahs(ayahs: List<Ayah>)
    suspend fun saveMetadata(metadata: GeneratorMetadata)
}
