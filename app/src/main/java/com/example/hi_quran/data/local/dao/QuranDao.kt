package com.example.hi_quran.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.hi_quran.data.local.entity.AyahEntity
import com.example.hi_quran.data.local.entity.SurahEntity
import com.example.hi_quran.data.local.entity.relation.SurahWithAyahs
import kotlinx.coroutines.flow.Flow

@Dao
interface QuranDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSurahs(surahs: List<SurahEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAyahs(ayahs: List<AyahEntity>)

    @Query("SELECT * FROM surah ORDER BY number ASC")
    fun getAllSurahs(): Flow<List<SurahEntity>>

    @Transaction
    @Query("SELECT * FROM surah WHERE number = :surahNumber")
    fun getSurahWithAyahs(surahNumber: Int): Flow<SurahWithAyahs>

    @Query("SELECT * FROM surah WHERE number = :surahNumber")
    suspend fun getSurahByNumber(surahNumber: Int): SurahEntity?

    @Query("SELECT * FROM ayah WHERE surah_number = :surahNumber ORDER BY number ASC")
    fun getAyahsBySurah(surahNumber: Int): Flow<List<AyahEntity>>

    @Query("SELECT COUNT(*) FROM surah")
    suspend fun getSurahCount(): Int
}
