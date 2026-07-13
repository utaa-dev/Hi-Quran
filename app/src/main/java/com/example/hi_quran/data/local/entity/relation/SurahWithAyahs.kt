package com.example.hi_quran.data.local.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.hi_quran.data.local.entity.AyahEntity
import com.example.hi_quran.data.local.entity.SurahEntity

data class SurahWithAyahs(
    @Embedded val surah: SurahEntity,
    @Relation(
        parentColumn = "number",
        entityColumn = "surah_number"
    )
    val ayahs: List<AyahEntity>
)
