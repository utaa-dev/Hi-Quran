package com.example.hi_quran.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "juz")
data class JuzEntity(
    @PrimaryKey val number: Int,
    val name: String,
    val startSurahNumber: Int,
    val startAyahNumber: Int
)
