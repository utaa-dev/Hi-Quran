package com.example.hi_quran.data.local.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.hi_quran.data.local.entity.AyahEntity
import com.example.hi_quran.data.local.entity.JuzEntity

data class JuzWithAyahs(
    @Embedded val juz: JuzEntity,
    @Relation(
        parentColumn = "number",
        entityColumn = "juz"
    )
    val ayahs: List<AyahEntity>
)
