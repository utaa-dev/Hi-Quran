package com.example.hi_quran.data.local.dao

import androidx.room.*
import com.example.hi_quran.data.local.entity.JuzEntity
import com.example.hi_quran.data.local.entity.relation.JuzWithAyahs
import kotlinx.coroutines.flow.Flow

@Dao
interface JuzDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJuzList(juzList: List<JuzEntity>)

    @Query("SELECT * FROM juz ORDER BY number ASC")
    fun getAllJuz(): Flow<List<JuzEntity>>

    @Transaction
    @Query("SELECT * FROM juz WHERE number = :juzNumber")
    fun getJuzWithAyahs(juzNumber: Int): Flow<JuzWithAyahs>

    @Query("SELECT COUNT(*) FROM juz")
    suspend fun getJuzCount(): Int
}
