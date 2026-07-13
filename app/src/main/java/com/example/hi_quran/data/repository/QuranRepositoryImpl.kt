package com.example.hi_quran.data.repository

import com.example.hi_quran.data.local.dao.*
import com.example.hi_quran.data.local.entity.*
import com.example.hi_quran.domain.model.*
import com.example.hi_quran.domain.repository.QuranRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuranRepositoryImpl(
    private val quranDao: QuranDao,
    private val juzDao: JuzDao,
    private val bookmarkDao: BookmarkDao,
    private val lastReadDao: LastReadDao,
    private val doaDao: DoaDao
) : QuranRepository {

    override fun getAllSurahs(): Flow<List<Surah>> {
        return quranDao.getAllSurahs().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getAyahsBySurah(surahNumber: Int): Flow<List<Ayah>> {
        return quranDao.getAyahsBySurah(surahNumber).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getSurahByNumber(surahNumber: Int): Surah? {
        return quranDao.getSurahByNumber(surahNumber)?.toDomain()
    }

    override suspend fun getSurahCount(): Int {
        return quranDao.getSurahCount()
    }

    override suspend fun isDatabaseEmpty(): Boolean {
        return quranDao.getSurahCount() == 0
    }

    // Juz
    override fun getAllJuz(): Flow<List<Juz>> {
        return juzDao.getAllJuz().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getJuzWithAyahs(juzNumber: Int): Flow<Pair<Juz, List<Ayah>>> {
        return juzDao.getJuzWithAyahs(juzNumber).map { juzWithAyahs ->
            Pair(
                juzWithAyahs.juz.toDomain(),
                juzWithAyahs.ayahs.map { it.toDomain() }
            )
        }
    }

    // Bookmark
    override fun getAllBookmarks(): Flow<List<Bookmark>> {
        return bookmarkDao.getAllBookmarks().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addBookmark(bookmark: Bookmark) {
        bookmarkDao.addBookmark(bookmark.toEntity())
    }

    override suspend fun deleteBookmark(bookmark: Bookmark) {
        bookmarkDao.deleteBookmark(bookmark.toEntity())
    }

    override fun isBookmarked(surahNumber: Int, ayahNumber: Int): Flow<Boolean> {
        return bookmarkDao.isBookmarked(surahNumber, ayahNumber)
    }

    // Last Read
    override fun getLastRead(): Flow<LastRead?> {
        return lastReadDao.getLastRead().map { it?.toDomain() }
    }

    override suspend fun updateLastRead(lastRead: LastRead) {
        lastReadDao.updateLastRead(lastRead.toEntity())
    }

    // Doa
    override fun getAllDoas(): Flow<List<Doa>> {
        return doaDao.getAllDoas().map { entities ->
            entities.map { it.toDomain() }
        }
    }
}

// Mappers
fun JuzEntity.toDomain() = Juz(number, name, startSurahNumber, startAyahNumber)
fun Juz.toEntity() = JuzEntity(number, name, startSurahNumber, startAyahNumber)

fun BookmarkEntity.toDomain() = Bookmark(id, surahNumber, ayahNumber, surahName, createdAt)
fun Bookmark.toEntity() = BookmarkEntity(id, surahNumber, ayahNumber, surahName, createdAt)

fun LastReadEntity.toDomain() = LastRead(surahNumber, ayahNumber, surahName, timestamp)
fun LastRead.toEntity() = LastReadEntity(surahNumber = surahNumber, ayahNumber = ayahNumber, surahName = surahName, timestamp = timestamp)
