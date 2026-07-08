package com.example.hi_quran.domain.usecase.juz

import com.example.hi_quran.domain.model.Juz
import com.example.hi_quran.domain.repository.QuranRepository
import kotlinx.coroutines.flow.Flow

class GetJuzUseCase(private val repository: QuranRepository) {
    operator fun invoke(): Flow<List<Juz>> = repository.getAllJuz()
}
