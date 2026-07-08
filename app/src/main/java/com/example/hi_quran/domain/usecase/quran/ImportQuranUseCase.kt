package com.example.hi_quran.domain.usecase.quran

import android.util.Log
import com.example.hi_quran.data.local.datasource.QuranLocalDataSource
import com.example.hi_quran.domain.model.*
import com.example.hi_quran.domain.repository.QuranRepository

class ImportQuranUseCase(
    private val repository: QuranRepository,
    private val localDataSource: QuranLocalDataSource
) {
    suspend operator fun invoke() {
        try {
            if (!repository.isDataImported()) {
                Log.d("ImportQuranUseCase", "Starting data import...")
                
                // 1. Import Quran Data (Surahs & Ayahs)
                localDataSource.getQuranFromAssets().onSuccess { quranDto ->
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
                }.onFailure { e ->
                    Log.e("ImportQuranUseCase", "Failed to import Quran data", e)
                }

                // 2. Import Juz Data
                localDataSource.getJuzFromAssets().onSuccess { juzDto ->
                    val juzList = juzDto.juzList.map {
                        Juz(it.number, it.name, it.startSurahNumber, it.startAyahNumber)
                    }
                    repository.importJuzData(juzList)
                    Log.d("ImportQuranUseCase", "Juz data imported successfully")
                }.onFailure { e ->
                    Log.e("ImportQuranUseCase", "Failed to import Juz data", e)
                }

                // 3. Import Doa Data
                localDataSource.getDoasFromAssets().onSuccess { doaDto ->
                    val doas = doaDto.doas.map {
                        Doa(it.id, it.title, it.arabic, it.translation, it.source)
                    }
                    repository.importDoaData(doas)
                    Log.d("ImportQuranUseCase", "Doa data imported successfully")
                }.onFailure { e ->
                    Log.e("ImportQuranUseCase", "Failed to import Doa data", e)
                }
                
                Log.d("ImportQuranUseCase", "Data import completed")
            }
        } catch (e: Exception) {
            Log.e("ImportQuranUseCase", "Unexpected error during import", e)
        }
    }
}
