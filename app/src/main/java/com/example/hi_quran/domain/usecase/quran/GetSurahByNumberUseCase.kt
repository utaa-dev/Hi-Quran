package com.example.hi_quran.domain.usecase.quran

import com.example.hi_quran.domain.model.Surah
import com.example.hi_quran.domain.repository.QuranRepository

class GetSurahByNumberUseCase(
    private val repository: QuranRepository
) {
    suspend operator fun invoke(surahNumber: Int): Surah? {
        return repository.getSurahByNumber(surahNumber)
    }
}
