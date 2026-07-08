package com.example.hi_quran.domain.usecase.quran

import com.example.hi_quran.domain.model.Surah
import com.example.hi_quran.domain.repository.QuranRepository
import kotlinx.coroutines.flow.Flow

class GetSurahsUseCase(
    private val repository: QuranRepository
) {
    operator fun invoke(): Flow<List<Surah>> {
        return repository.getAllSurahs()
    }
}
