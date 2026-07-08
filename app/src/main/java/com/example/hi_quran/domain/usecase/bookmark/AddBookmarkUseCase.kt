package com.example.hi_quran.domain.usecase.bookmark

import com.example.hi_quran.domain.model.Bookmark
import com.example.hi_quran.domain.repository.QuranRepository

class AddBookmarkUseCase(private val repository: QuranRepository) {
    suspend operator fun invoke(bookmark: Bookmark) = repository.addBookmark(bookmark)
}
