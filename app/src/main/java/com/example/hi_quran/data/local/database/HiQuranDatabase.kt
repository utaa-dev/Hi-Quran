package com.example.hi_quran.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hi_quran.data.local.dao.*
import com.example.hi_quran.data.local.entity.*

@Database(
    entities = [
        SurahEntity::class,
        AyahEntity::class,
        DoaEntity::class,
        JuzEntity::class,
        BookmarkEntity::class,
        LastReadEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class HiQuranDatabase : RoomDatabase() {
    abstract fun quranDao(): QuranDao
    abstract fun doaDao(): DoaDao
    abstract fun juzDao(): JuzDao
    abstract fun bookmarkDao(): BookmarkDao
    abstract fun lastReadDao(): LastReadDao

    companion object {
        const val DATABASE_NAME = "hi_quran_db"
    }
}
