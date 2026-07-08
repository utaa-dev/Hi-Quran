package com.example.hi_quran.feature.quran.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hi_quran.domain.model.*
import com.example.hi_quran.domain.usecase.bookmark.AddBookmarkUseCase
import com.example.hi_quran.domain.usecase.juz.GetJuzDetailUseCase
import com.example.hi_quran.domain.usecase.lastread.UpdateLastReadUseCase
import com.example.hi_quran.domain.usecase.quran.GetSurahsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JuzDetailViewModel @Inject constructor(
    private val getJuzDetailUseCase: GetJuzDetailUseCase,
    private val getSurahsUseCase: GetSurahsUseCase,
    private val updateLastReadUseCase: UpdateLastReadUseCase,
    private val addBookmarkUseCase: AddBookmarkUseCase
) : ViewModel() {

    private val _juz = MutableStateFlow<Juz?>(null)
    val juz: StateFlow<Juz?> = _juz.asStateFlow()

    private val _ayahs = MutableStateFlow<List<Ayah>>(emptyList())
    val ayahs: StateFlow<List<Ayah>> = _ayahs.asStateFlow()

    private val _surahNames = MutableStateFlow<Map<Int, String>>(emptyMap())
    val surahNames: StateFlow<Map<Int, String>> = _surahNames.asStateFlow()

    fun loadJuzDetail(juzNumber: Int) {
        // Load Surahs to get names
        getSurahsUseCase()
            .onEach { surahs ->
                _surahNames.value = surahs.associate { it.number to it.englishName }
            }
            .launchIn(viewModelScope)

        getJuzDetailUseCase(juzNumber)
            .onEach { (juz, ayahs) ->
                _juz.value = juz
                _ayahs.value = ayahs
            }
            .launchIn(viewModelScope)
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
