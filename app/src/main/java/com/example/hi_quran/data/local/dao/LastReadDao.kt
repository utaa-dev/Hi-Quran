package com.example.hi_quran.data.local.dao

import androidx.room.*
import com.example.hi_quran.data.local.entity.LastReadEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LastReadDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateLastRead(lastRead: LastReadEntity)

    @Query("SELECT * FROM last_read WHERE id = 1")
    fun getLastRead(): Flow<LastReadEntity?>
}
