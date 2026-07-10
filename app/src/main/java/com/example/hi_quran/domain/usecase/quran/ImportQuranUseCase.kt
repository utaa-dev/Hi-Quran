package com.example.hi_quran.domain.usecase.quran

import android.util.Log
import com.example.hi_quran.data.local.datasource.QuranLocalDataSource
import com.example.hi_quran.domain.model.*
import com.example.hi_quran.domain.repository.QuranRepository
import kotlinx.coroutines.flow.firstOrNull

class ImportQuranUseCase(
    private val repository: QuranRepository,
    private val localDataSource: QuranLocalDataSource
) {
    suspend operator fun invoke() {
        try {
            Log.d("ImportQuranUseCase", "Checking data for import...")
            
            localDataSource.getQuranFromAssets().onSuccess { quranDto ->
                val localSurahCount = repository.getSurahCount()
                val jsonSurahCount = quranDto.surahs.size

                if (jsonSurahCount > localSurahCount) {
                    Log.d("ImportQuranUseCase", "New surahs detected ($jsonSurahCount vs $localSurahCount). Importing...")
                    
                    val surahs = quranDto.surahs.map { surahDto ->
                        Surah(
                            number = surahDto.number,
                            name = surahDto.name,
                            englishName = surahDto.englishName,
                            englishNameTranslation = surahDto.englishNameTranslation,
                            numberOfAyahs = surahDto.ayahs.size,
                            revelationType = surahDto.revelationType
                        )
                    }
                    val ayahs = quranDto.surahs.flatMap { surahDto ->
                        surahDto.ayahs.map { ayahDto ->
                            Ayah(
                                number = ayahDto.number,
                                text = ayahDto.text,
                                transliteration = ayahDto.transliteration,
                                translation = ayahDto.translation,
                                surahNumber = surahDto.number,
                                juz = ayahDto.juz,
                                manzil = 0,
                                page = ayahDto.page,
                                ruku = 0,
                                hizbQuarter = 0,
                                sajda = false
                            )
                        }
                    }
                    repository.importQuranData(surahs, ayahs)
                    Log.d("ImportQuranUseCase", "Quran data imported successfully")
                } else {
                    Log.d("ImportQuranUseCase", "Quran data is already up to date")
                }
            }.onFailure { e ->
                Log.e("ImportQuranUseCase", "Failed to read Quran JSON", e)
            }

            // 2. Import Juz Data (Hanya jika belum ada)
            if (repository.getAllJuz().firstOrNull()?.isEmpty() == true) {
                localDataSource.getJuzFromAssets().onSuccess { juzDto ->
                    val juzList = juzDto.juzList.map {
                        Juz(it.number, it.name, it.startSurahNumber, it.startAyahNumber)
                    }
                    repository.importJuzData(juzList)
                }
            }

            // 3. Import Doa Data (Hanya jika belum ada)
            if (repository.getAllDoas().firstOrNull()?.isEmpty() == true) {
                localDataSource.getDoasFromAssets().onSuccess { doaDto ->
                    val doas = doaDto.doas.map {
                        Doa(it.id, it.title, it.arabic, it.translation, it.source)
                    }
                    repository.importDoaData(doas)
                }
            }
        } catch (e: Exception) {
            Log.e("ImportQuranUseCase", "Unexpected error during import", e)
        }
    }
}
