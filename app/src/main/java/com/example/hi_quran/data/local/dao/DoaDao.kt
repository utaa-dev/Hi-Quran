package com.example.hi_quran.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hi_quran.data.local.entity.DoaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DoaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDoas(doas: List<DoaEntity>)

    @Query("SELECT * FROM doa ORDER BY id ASC")
    fun getAllDoas(): Flow<List<DoaEntity>>

    @Query("SELECT COUNT(*) FROM doa")
    suspend fun getDoaCount(): Int
}
