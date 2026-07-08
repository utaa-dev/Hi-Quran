package com.example.hi_quran.domain.usecase.lastread

import com.example.hi_quran.domain.model.LastRead
import com.example.hi_quran.domain.repository.QuranRepository

class UpdateLastReadUseCase(
    private val repository: QuranRepository
) {
    suspend operator fun invoke(lastRead: LastRead) {
        repository.updateLastRead(lastRead)
    }
}
