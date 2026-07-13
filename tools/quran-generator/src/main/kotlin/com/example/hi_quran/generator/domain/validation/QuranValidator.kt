package com.example.hi_quran.generator.domain.validation

import com.example.hi_quran.generator.domain.model.Ayah
import com.example.hi_quran.generator.domain.model.GeneratorMetadata
import com.example.hi_quran.generator.domain.model.Surah

object QuranValidator {

    fun validateSurahList(surahs: List<Surah>) {
        if (surahs.size != 114) {
            throw ValidationException("Invalid total surah count: ${surahs.size}, expected 114")
        }

        val surahNumbers = surahs.map { it.number }
        if (surahNumbers.distinct().size != surahs.size) {
            throw ValidationException("Duplicate surah numbers found")
        }

        surahs.forEachIndexed { index, surah ->
            val expectedNumber = index + 1
            if (surah.number != expectedNumber) {
                throw ValidationException("Surah sequence broken: expected #$expectedNumber but found #${surah.number}")
            }
            validateSurah(surah)
        }
    }

    fun validateSurah(surah: Surah) {
        if (surah.name.isBlank()) throw ValidationException("Surah #${surah.number} name is blank")
        if (surah.englishName.isBlank()) throw ValidationException("Surah #${surah.number} englishName is blank")
        if (surah.translation.isBlank()) throw ValidationException("Surah #${surah.number} translation is blank")
        if (surah.numberOfAyahs <= 0) throw ValidationException("Surah #${surah.number} has invalid ayah count: ${surah.numberOfAyahs}")
        if (surah.revelationType !in listOf("Meccan", "Medinan")) {
            throw ValidationException("Surah #${surah.number} has invalid revelationType: ${surah.revelationType}")
        }
    }

    fun validateAyahList(surah: Surah, ayahs: List<Ayah>) {
        if (ayahs.size != surah.numberOfAyahs) {
            throw ValidationException("Ayah count mismatch for Surah #${surah.number}: found ${ayahs.size}, expected ${surah.numberOfAyahs}")
        }

        val ayahNumbers = ayahs.map { it.number }
        if (ayahNumbers.distinct().size != ayahs.size) {
            throw ValidationException("Duplicate ayah numbers found in Surah #${surah.number}")
        }

        ayahs.forEachIndexed { index, ayah ->
            val expectedNumber = index + 1
            if (ayah.number != expectedNumber) {
                throw ValidationException("Ayah sequence broken in Surah #${surah.number}: expected ayah #$expectedNumber but found #${ayah.number}")
            }
            if (ayah.surahNumber != surah.number) {
                throw ValidationException("Ayah #${ayah.number} surahNumber mismatch: found ${ayah.surahNumber}, expected ${surah.number}")
            }
            validateAyah(ayah)
        }
    }

    fun validateAyah(ayah: Ayah) {
        val identifier = "Surah #${ayah.surahNumber} Ayah #${ayah.number}"
        if (ayah.textArabic.isBlank()) throw ValidationException("$identifier: textArabic is blank")
        if (ayah.textArabicPlain.isBlank()) throw ValidationException("$identifier: textArabicPlain is blank")
        if (ayah.textLatin.isBlank()) throw ValidationException("$identifier: textLatin is blank")
        if (ayah.textIndonesia.isBlank()) throw ValidationException("$identifier: textIndonesia is blank")
    }

    fun validateMetadata(metadata: GeneratorMetadata) {
        if (metadata.version.isBlank()) throw ValidationException("Metadata: version is blank")
        if (metadata.generatedAt.isBlank()) throw ValidationException("Metadata: generatedAt is blank")
        if (metadata.apiSource.isBlank()) throw ValidationException("Metadata: apiSource is blank")
        if (metadata.sourceLicense.isBlank()) throw ValidationException("Metadata: sourceLicense is blank")
        if (metadata.locale.isBlank()) throw ValidationException("Metadata: locale is blank")
        if (metadata.generatedBy.isBlank()) throw ValidationException("Metadata: generatedBy is blank")
    }
}
