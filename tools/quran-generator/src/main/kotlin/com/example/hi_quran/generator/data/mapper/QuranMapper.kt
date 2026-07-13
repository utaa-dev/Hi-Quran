package com.example.hi_quran.generator.data.mapper

import com.example.hi_quran.generator.domain.model.Ayah
import com.example.hi_quran.generator.domain.model.GeneratorMetadata
import com.example.hi_quran.generator.domain.model.Surah
import com.example.hi_quran.generator.model.SurahItemDto
import com.example.hi_quran.generator.model.AyahDto
import com.example.hi_quran.generator.util.ArabicNormalizer

object QuranMapper {

    fun mapToDomain(dto: SurahItemDto): Surah {
        return Surah(
            number = dto.nomor,
            name = dto.nama,
            englishName = dto.namaLatin,
            translation = dto.arti,
            numberOfAyahs = dto.jumlahAyat,
            revelationType = if (dto.tempatTurun.equals("Mekah", ignoreCase = true)) "Meccan" else "Medinan"
        )
    }

    fun mapToDomain(dto: AyahDto, surahNumber: Int): Ayah {
        return Ayah(
            surahNumber = surahNumber,
            number = dto.nomorAyat,
            textArabic = dto.teksArab,
            textArabicPlain = ArabicNormalizer.normalize(dto.teksArab),
            textLatin = dto.teksLatin,
            textIndonesia = dto.teksIndonesia
        )
    }

    fun mapToMap(surah: Surah): Map<String, Any?> {
        return mapOf(
            "number" to surah.number,
            "name" to surah.name,
            "english_name" to surah.englishName,
            "english_name_translation" to surah.translation,
            "number_of_ayahs" to surah.numberOfAyahs,
            "revelation_type" to surah.revelationType
        )
    }

    fun mapToMap(ayah: Ayah): Map<String, Any?> {
        return mapOf(
            "surah_number" to ayah.surahNumber,
            "number" to ayah.number,
            "text_arabic" to ayah.textArabic,
            "text_arabic_plain" to ayah.textArabicPlain,
            "text_latin" to ayah.textLatin,
            "text_indonesia" to ayah.textIndonesia,
            "juz" to ayah.juz,
            "manzil" to ayah.manzil,
            "page" to ayah.page,
            "ruku" to ayah.ruku,
            "hizb_quarter" to ayah.hizbQuarter,
            "sajda" to if (ayah.sajda) 1 else 0
        )
    }

    fun mapToMap(metadata: GeneratorMetadata): Map<String, String> {
        return mapOf(
            "version" to metadata.version,
            "generated_at" to metadata.generatedAt,
            "api_source" to metadata.apiSource,
            "source_license" to metadata.sourceLicense,
            "locale" to metadata.locale,
            "generated_by" to metadata.generatedBy
        )
    }
}
