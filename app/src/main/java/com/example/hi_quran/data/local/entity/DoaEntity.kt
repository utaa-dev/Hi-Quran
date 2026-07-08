package com.example.hi_quran.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hi_quran.domain.model.Doa

@Entity(tableName = "doa")
data class DoaEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val arabic: String,
    val translation: String,
    val source: String
)

fun DoaEntity.toDomain() = Doa(
    id = id,
    title = title,
    arabic = arabic,
    translation = translation,
    source = source
)

fun Doa.toEntity() = DoaEntity(
    id = id,
    title = title,
    arabic = arabic,
    translation = translation,
    source = source
)
