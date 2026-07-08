package com.example.hi_quran.domain.usecase.lastread

import com.example.hi_quran.domain.model.LastRead
import com.example.hi_quran.domain.repository.QuranRepository
import kotlinx.coroutines.flow.Flow

class GetLastReadUseCase(
    private val repository: QuranRepository
) {
    operator fun invoke(): Flow<LastRead?> {
        return repository.getLastRead()
    }
}
