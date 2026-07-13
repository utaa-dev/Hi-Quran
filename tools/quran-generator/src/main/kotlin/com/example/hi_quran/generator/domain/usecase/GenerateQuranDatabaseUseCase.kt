package com.example.hi_quran.generator.domain.usecase

import com.example.hi_quran.generator.domain.model.GeneratorMetadata
import com.example.hi_quran.generator.domain.repository.QuranRepository
import com.example.hi_quran.generator.domain.validation.QuranValidator
import java.time.OffsetDateTime

class GenerateQuranDatabaseUseCase(
    private val quranRepository: QuranRepository
) {
    /**
     * Menjalankan pipeline orkestrasi generator di level domain.
     * 1. Fetch Daftar Surah
     * 2. Save Daftar Surah
     * 3. Fetch Ayah per Surah dalam loop
     * 4. Save Ayah
     * 5. Save Metadata
     */
    suspend operator fun invoke(
        params: Params,
        onProgress: (currentIndex: Int, total: Int, surahNumber: Int, surahName: String) -> Unit = { _, _, _, _ -> }
    ): Result {
        var totalAyahs = 0
        
        // 1. Fetch Daftar Surah
        val surahs = quranRepository.fetchSurahs()
        
        // Validate Surah List
        QuranValidator.validateSurahList(surahs)
        
        // 2. Save Daftar Surah
        quranRepository.saveSurahs(surahs)
        
        // 3. Process each Surah
        surahs.forEachIndexed { index, surah ->
            // Update Progress
            onProgress(index, surahs.size, surah.number, surah.englishName)

            // 4. Fetch Ayahs
            val ayahs = quranRepository.fetchAyahs(surah.number)
            
            // Validate Ayah List
            QuranValidator.validateAyahList(surah, ayahs)
            
            // 5. Save Ayahs
            quranRepository.saveAyahs(ayahs)
            totalAyahs += ayahs.size
        }
        
        // 6. Create Metadata
        val metadata = GeneratorMetadata(
            version = params.dbVersion,
            generatedAt = OffsetDateTime.now().toString(),
            apiSource = params.apiSource,
            sourceLicense = params.sourceLicense,
            locale = params.locale,
            generatedBy = params.generatedBy
        )
        
        // Validate Metadata
        QuranValidator.validateMetadata(metadata)
        
        // 7. Save Metadata
        quranRepository.saveMetadata(metadata)

        return Result(
            totalSurahs = surahs.size,
            totalAyahs = totalAyahs
        )
    }

    data class Params(
        val dbVersion: String,
        val apiSource: String,
        val sourceLicense: String = "CC BY-SA 4.0",
        val locale: String = "id-ID",
        val generatedBy: String = "Hi-Quran Generator"
    )

    data class Result(
        val totalSurahs: Int,
        val totalAyahs: Int
    )
}
