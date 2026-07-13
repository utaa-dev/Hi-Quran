package com.example.hi_quran.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hi_quran.domain.model.Doa

@Entity(tableName = "doa")
data class DoaEntity(
    @PrimaryKey 
    @ColumnInfo(name = "id")
    val id: Int,
    
    @ColumnInfo(name = "title")
    val title: String,
    
    @ColumnInfo(name = "arabic")
    val arabic: String,
    
    @ColumnInfo(name = "translation")
    val translation: String,
    
    @ColumnInfo(name = "source")
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
