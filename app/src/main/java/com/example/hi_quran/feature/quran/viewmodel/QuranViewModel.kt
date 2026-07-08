package com.example.hi_quran.feature.quran.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hi_quran.domain.model.Juz
import com.example.hi_quran.domain.model.Surah
import com.example.hi_quran.domain.usecase.juz.GetJuzUseCase
import com.example.hi_quran.domain.usecase.quran.GetSurahsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class QuranViewModel @Inject constructor(
    private val getSurahsUseCase: GetSurahsUseCase,
    private val getJuzUseCase: GetJuzUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _allSurahs = MutableStateFlow<List<Surah>>(emptyList())
    
    val surahs: StateFlow<List<Surah>> = combine(_allSurahs, _searchQuery) { surahs, query ->
        if (query.isBlank()) {
            surahs
        } else {
            surahs.filter { 
                it.englishName.contains(query, ignoreCase = true) || 
                it.name.contains(query, ignoreCase = true) 
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _juzList = MutableStateFlow<List<Juz>>(emptyList())
    val juzList: StateFlow<List<Juz>> = _juzList.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        getSurahsUseCase()
            .onEach { _allSurahs.value = it }
            .launchIn(viewModelScope)

        getJuzUseCase()
            .onEach { _juzList.value = it }
            .launchIn(viewModelScope)
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }
}
