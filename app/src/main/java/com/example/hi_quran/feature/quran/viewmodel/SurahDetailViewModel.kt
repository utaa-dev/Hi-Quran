package com.example.hi_quran.feature.quran.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hi_quran.domain.model.Ayah
import com.example.hi_quran.domain.model.Bookmark
import com.example.hi_quran.domain.model.LastRead
import com.example.hi_quran.domain.model.Surah
import com.example.hi_quran.domain.usecase.bookmark.AddBookmarkUseCase
import com.example.hi_quran.domain.usecase.lastread.UpdateLastReadUseCase
import com.example.hi_quran.domain.usecase.quran.GetAyahsBySurahUseCase
import com.example.hi_quran.domain.usecase.quran.GetSurahByNumberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SurahDetailViewModel @Inject constructor(
    private val getAyahsBySurahUseCase: GetAyahsBySurahUseCase,
    private val getSurahByNumberUseCase: GetSurahByNumberUseCase,
    private val updateLastReadUseCase: UpdateLastReadUseCase,
    private val addBookmarkUseCase: AddBookmarkUseCase
) : ViewModel() {

    private val _surah = MutableStateFlow<Surah?>(null)
    val surah: StateFlow<Surah?> = _surah.asStateFlow()

    private val _ayahs = MutableStateFlow<List<Ayah>>(emptyList())
    val ayahs: StateFlow<List<Ayah>> = _ayahs.asStateFlow()

    fun loadSurahDetail(surahNumber: Int) {
        viewModelScope.launch {
            _surah.value = getSurahByNumberUseCase(surahNumber)
            getAyahsBySurahUseCase(surahNumber)
                .onEach { _ayahs.value = it }
                .launchIn(viewModelScope)
        }
    }

    fun updateLastRead(ayah: Ayah, surahName: String) {
        viewModelScope.launch {
            updateLastReadUseCase(
                LastRead(
                    surahNumber = ayah.surahNumber,
                    ayahNumber = ayah.number,
                    surahName = surahName,
                    timestamp = System.currentTimeMillis()
                )
            )
        }
    }

    fun addBookmark(ayah: Ayah, surahName: String) {
        viewModelScope.launch {
            addBookmarkUseCase(
                Bookmark(
                    surahNumber = ayah.surahNumber,
                    ayahNumber = ayah.number,
                    surahName = surahName,
                    createdAt = System.currentTimeMillis()
                )
            )
        }
    }
}
