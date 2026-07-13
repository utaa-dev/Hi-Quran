package com.example.hi_quran.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "juz")
data class JuzEntity(
    @PrimaryKey 
    @ColumnInfo(name = "number")
    val number: Int,
    
    @ColumnInfo(name = "name")
    val name: String,
    
    @ColumnInfo(name = "start_surah_number")
    val startSurahNumber: Int,
    
    @ColumnInfo(name = "start_ayah_number")
    val startAyahNumber: Int
)
