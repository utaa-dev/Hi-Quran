package com.example.hi_quran.domain.usecase.bookmark

import com.example.hi_quran.domain.model.Bookmark
import com.example.hi_quran.domain.repository.QuranRepository
import kotlinx.coroutines.flow.Flow

class GetBookmarksUseCase(
    private val repository: QuranRepository
) {
    operator fun invoke(): Flow<List<Bookmark>> {
        return repository.getAllBookmarks()
    }
}
