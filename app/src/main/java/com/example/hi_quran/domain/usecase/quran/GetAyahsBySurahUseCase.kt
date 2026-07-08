package com.example.hi_quran.domain.usecase.quran

import com.example.hi_quran.domain.model.Ayah
import com.example.hi_quran.domain.repository.QuranRepository
import kotlinx.coroutines.flow.Flow

class GetAyahsBySurahUseCase(
    private val repository: QuranRepository
) {
    operator fun invoke(surahNumber: Int): Flow<List<Ayah>> {
        return repository.getAyahsBySurah(surahNumber)
    }
}
