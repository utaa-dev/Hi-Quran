package com.example.hi_quran.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hi_quran.domain.model.LastRead
import com.example.hi_quran.domain.model.Surah
import com.example.hi_quran.domain.usecase.lastread.GetLastReadUseCase
import com.example.hi_quran.domain.usecase.quran.GetSurahsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSurahsUseCase: GetSurahsUseCase,
    private val getLastReadUseCase: GetLastReadUseCase
) : ViewModel() {

    private val _surahs = MutableStateFlow<List<Surah>>(emptyList())
    val surahs: StateFlow<List<Surah>> = _surahs.asStateFlow()

    val lastRead: StateFlow<LastRead?> = getLastReadUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    init {
        loadSurahs()
    }

    private fun loadSurahs() {
        getSurahsUseCase()
            .onEach { _surahs.value = it }
            .launchIn(viewModelScope)
    }
}
