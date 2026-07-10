package com.example.hi_quran.domain.usecase.doa

import com.example.hi_quran.domain.model.Doa
import com.example.hi_quran.domain.repository.QuranRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDoasUseCase @Inject constructor(
    private val repository: QuranRepository
) {
    operator fun invoke(): Flow<List<Doa>> {
        return repository.getAllDoas()
    }
}
