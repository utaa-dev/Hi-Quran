package com.example.hi_quran.data.remote.mapper

import com.example.hi_quran.data.local.entity.SurahEntity
import com.example.hi_quran.data.remote.dto.RemoteSurahDto

fun RemoteSurahDto.toEntity(): SurahEntity {
    return SurahEntity(
        number = this.nomor,
        name = this.nama,
        englishName = this.namaLatin,
        englishNameTranslation = this.arti,
        numberOfAyahs = this.jumlahAyat,
        revelationType = if (this.tempatTurun.equals("Mekah", ignoreCase = true)) "Meccan" else "Medinan"
    )
}

fun List<RemoteSurahDto>.toEntities(): List<SurahEntity> {
    return this.map { it.toEntity() }
}
