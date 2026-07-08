package com.example.hi_quran.domain.usecase.juz

import com.example.hi_quran.domain.model.Ayah
import com.example.hi_quran.domain.model.Juz
import com.example.hi_quran.domain.repository.QuranRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetJuzDetailUseCase @Inject constructor(
    private val repository: QuranRepository
) {
    operator fun invoke(juzNumber: Int): Flow<Pair<Juz, List<Ayah>>> {
        return repository.getJuzWithAyahs(juzNumber)
    }
}
