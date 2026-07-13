package com.example.hi_quran.generator.data.datasource.local

import com.example.hi_quran.generator.domain.model.Ayah
import com.example.hi_quran.generator.domain.model.GeneratorMetadata
import com.example.hi_quran.generator.domain.model.Surah

interface QuranLocalDataSource {
    suspend fun saveSurahs(surahs: List<Surah>)
    suspend fun saveAyahs(ayahs: List<Ayah>)
    suspend fun saveMetadata(metadata: GeneratorMetadata)
}
