package com.example.hi_quran.generator.domain.validation

import com.example.hi_quran.generator.domain.model.Ayah
import com.example.hi_quran.generator.domain.model.GeneratorMetadata
import com.example.hi_quran.generator.domain.model.Surah
import kotlin.test.Test
import kotlin.test.assertFailsWith

class QuranValidatorTest {

    private fun createValidSurah(number: Int = 1, numberOfAyahs: Int = 7) = Surah(
        number = number,
        name = "Al-Fatihah",
        englishName = "The Opening",
        translation = "Pembukaan",
        numberOfAyahs = numberOfAyahs,
        revelationType = "Meccan"
    )

    private fun createValidAyah(surahNumber: Int = 1, number: Int = 1) = Ayah(
        surahNumber = surahNumber,
        number = number,
        textArabic = "Ar",
        textArabicPlain = "Ar",
        textLatin = "Lat",
        textIndonesia = "Ind"
    )

    private fun createValidMetadata() = GeneratorMetadata(
        version = "1",
        generatedAt = "now",
        apiSource = "source",
        sourceLicense = "CC",
        locale = "id-ID",
        generatedBy = "Hi-Quran"
    )

    @Test
    fun `validateSurahList throws if count is not 114`() {
        val surahs = List(113) { createValidSurah(it + 1) }
        assertFailsWith<ValidationException> {
            QuranValidator.validateSurahList(surahs)
        }
    }

    @Test
    fun `validateSurahList throws if sequence is broken`() {
        val surahs = List(114) { index ->
            if (index == 1) createValidSurah(3) // skip number 2
            else createValidSurah(index + 1)
        }
        assertFailsWith<ValidationException> {
            QuranValidator.validateSurahList(surahs)
        }
    }

    @Test
    fun `validateSurah throws if fields are blank`() {
        val surah = createValidSurah().copy(name = "")
        assertFailsWith<ValidationException> {
            QuranValidator.validateSurah(surah)
        }
    }

    @Test
    fun `validateAyahList throws if count mismatch`() {
        val surah = createValidSurah(1, 7)
        val ayahs = List(6) { createValidAyah(1, it + 1) }
        assertFailsWith<ValidationException> {
            QuranValidator.validateAyahList(surah, ayahs)
        }
    }

    @Test
    fun `validateAyahList throws if duplicate number`() {
        val surah = createValidSurah(1, 2)
        val ayahs = listOf(createValidAyah(1, 1), createValidAyah(1, 1))
        assertFailsWith<ValidationException> {
            QuranValidator.validateAyahList(surah, ayahs)
        }
    }

    @Test
    fun `validateAyahList throws if sequence broken`() {
        val surah = createValidSurah(1, 2)
        val ayahs = listOf(createValidAyah(1, 1), createValidAyah(1, 3))
        assertFailsWith<ValidationException> {
            QuranValidator.validateAyahList(surah, ayahs)
        }
    }

    @Test
    fun `validateAyah throws if text is blank`() {
        val ayah = createValidAyah().copy(textArabic = " ")
        assertFailsWith<ValidationException> {
            QuranValidator.validateAyah(ayah)
        }
    }

    @Test
    fun `validateMetadata throws if fields are blank`() {
        val metadata = createValidMetadata().copy(version = "")
        assertFailsWith<ValidationException> {
            QuranValidator.validateMetadata(metadata)
        }
    }

    @Test
    fun `valid cases should pass`() {
        val surahs = List(114) { createValidSurah(it + 1) }
        QuranValidator.validateSurahList(surahs)

        val surah = createValidSurah(1, 1)
        val ayahs = listOf(createValidAyah(1, 1))
        QuranValidator.validateAyahList(surah, ayahs)

        QuranValidator.validateMetadata(createValidMetadata())
    }
}
