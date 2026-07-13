package com.example.hi_quran.generator.data.datasource.remote

import com.example.hi_quran.generator.api.QuranFetcher
import com.example.hi_quran.generator.data.mapper.QuranMapper
import com.example.hi_quran.generator.domain.model.Ayah
import com.example.hi_quran.generator.domain.model.Surah

class QuranRemoteDataSourceImpl(
    private val fetcher: QuranFetcher
) : QuranRemoteDataSource {

    override suspend fun fetchSurahs(): List<Surah> {
        return fetcher.fetchSurahList().map { QuranMapper.mapToDomain(it) }
    }

    override suspend fun fetchAyahs(surahNumber: Int): List<Ayah> {
        return fetcher.fetchSurahDetail(surahNumber).ayat.map {
            QuranMapper.mapToDomain(it, surahNumber)
        }
    }
}
