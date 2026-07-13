package com.example.hi_quran.domain.repository

import com.example.hi_quran.domain.model.*
import kotlinx.coroutines.flow.Flow

interface QuranRepository {
    // Surah & Ayah
    fun getAllSurahs(): Flow<List<Surah>>
    fun getAyahsBySurah(surahNumber: Int): Flow<List<Ayah>>
    suspend fun getSurahByNumber(surahNumber: Int): Surah?
    suspend fun getSurahCount(): Int
    suspend fun isDatabaseEmpty(): Boolean

    // Juz
    fun getAllJuz(): Flow<List<Juz>>
    fun getJuzWithAyahs(juzNumber: Int): Flow<Pair<Juz, List<Ayah>>>

    // Bookmark
    fun getAllBookmarks(): Flow<List<Bookmark>>
    suspend fun addBookmark(bookmark: Bookmark)
    suspend fun deleteBookmark(bookmark: Bookmark)
    fun isBookmarked(surahNumber: Int, ayahNumber: Int): Flow<Boolean>

    // Last Read
    fun getLastRead(): Flow<LastRead?>
    suspend fun updateLastRead(lastRead: LastRead)

    // Doa
    fun getAllDoas(): Flow<List<Doa>>
}
