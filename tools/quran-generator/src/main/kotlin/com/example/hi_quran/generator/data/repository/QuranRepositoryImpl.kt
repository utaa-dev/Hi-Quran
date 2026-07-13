package com.example.hi_quran.generator.data.repository

import com.example.hi_quran.generator.data.datasource.local.QuranLocalDataSource
import com.example.hi_quran.generator.data.datasource.remote.QuranRemoteDataSource
import com.example.hi_quran.generator.domain.model.Ayah
import com.example.hi_quran.generator.domain.model.GeneratorMetadata
import com.example.hi_quran.generator.domain.model.Surah
import com.example.hi_quran.generator.domain.repository.QuranRepository

class QuranRepositoryImpl(
    private val remoteDataSource: QuranRemoteDataSource,
    private val localDataSource: QuranLocalDataSource
) : QuranRepository {

    override suspend fun fetchSurahs(): List<Surah> {
        return remoteDataSource.fetchSurahs()
    }

    override suspend fun fetchAyahs(surahNumber: Int): List<Ayah> {
        return remoteDataSource.fetchAyahs(surahNumber)
    }

    override suspend fun saveSurahs(surahs: List<Surah>) {
        localDataSource.saveSurahs(surahs)
    }

    override suspend fun saveAyahs(ayahs: List<Ayah>) {
        localDataSource.saveAyahs(ayahs)
    }

    override suspend fun saveMetadata(metadata: GeneratorMetadata) {
        localDataSource.saveMetadata(metadata)
    }
}
