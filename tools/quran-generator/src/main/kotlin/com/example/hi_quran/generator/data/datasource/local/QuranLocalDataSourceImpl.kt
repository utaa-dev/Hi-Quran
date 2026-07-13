package com.example.hi_quran.generator.data.datasource.local

import com.example.hi_quran.generator.data.mapper.QuranMapper
import com.example.hi_quran.generator.database.DatabaseManager
import com.example.hi_quran.generator.domain.model.Ayah
import com.example.hi_quran.generator.domain.model.GeneratorMetadata
import com.example.hi_quran.generator.domain.model.Surah

class QuranLocalDataSourceImpl(
    private val dbManager: DatabaseManager
) : QuranLocalDataSource {

    override suspend fun saveSurahs(surahs: List<Surah>) {
        val maps = surahs.map { QuranMapper.mapToMap(it) }
        dbManager.insertSurahs(maps)
    }

    override suspend fun saveAyahs(ayahs: List<Ayah>) {
        val maps = ayahs.map { QuranMapper.mapToMap(it) }
        dbManager.insertAyahs(maps)
    }

    override suspend fun saveMetadata(metadata: GeneratorMetadata) {
        val map = QuranMapper.mapToMap(metadata)
        dbManager.saveMetadata(map)
    }
}
