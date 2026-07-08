package com.example.hi_quran.data.local.dao

import androidx.room.*
import com.example.hi_quran.data.local.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(bookmark: BookmarkEntity)

    @Delete
    suspend fun deleteBookmark(bookmark: BookmarkEntity)

    @Query("SELECT * FROM bookmark ORDER BY createdAt DESC")
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>

    @Query("SELECT EXISTS(SELECT * FROM bookmark WHERE surahNumber = :surahNumber AND ayahNumber = :ayahNumber)")
    fun isBookmarked(surahNumber: Int, ayahNumber: Int): Flow<Boolean>
}
